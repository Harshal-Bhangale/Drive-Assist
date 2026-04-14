package com.driveassist.rental.service;

import com.driveassist.common.dto.PagedResponse;
import com.driveassist.common.exception.BadRequestException;
import com.driveassist.common.exception.ResourceNotFoundException;
import com.driveassist.common.util.GeoUtils;
import com.driveassist.rental.dto.*;
import com.driveassist.rental.entity.Booking;
import com.driveassist.rental.entity.CarRental;
import com.driveassist.rental.enums.BookingStatus;
import com.driveassist.rental.enums.CarType;
import com.driveassist.rental.repository.BookingRepository;
import com.driveassist.rental.repository.CarRentalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;

/**
 * Concrete implementation — OOP Polymorphism.
 * Handles both car CRUD and booking business logic.
 */
@Service
public class RentalServiceImpl implements RentalService {

    private final CarRentalRepository carRentalRepository;
    private final BookingRepository bookingRepository;

    public RentalServiceImpl(CarRentalRepository carRentalRepository,
                             BookingRepository bookingRepository) {
        this.carRentalRepository = carRentalRepository;
        this.bookingRepository = bookingRepository;
    }

    // ========== Car CRUD ==========

    @Override
    @Transactional
    public CarRentalResponse create(CarRentalRequest request) {
        CarRental entity = mapToEntity(request, new CarRental());
        entity.setAvailable(true);
        return toCarResponse(carRentalRepository.save(entity), 0);
    }

    @Override
    public CarRentalResponse getById(Long id) {
        return toCarResponse(findCarById(id), 0);
    }

    @Override
    public PagedResponse<CarRentalResponse> getAll(int page, int size, String sortBy) {
        Page<CarRental> carPage = carRentalRepository.findAll(
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sortBy))
        );
        return new PagedResponse<>(
                carPage.getContent().stream().map(c -> toCarResponse(c, 0)).toList(),
                carPage.getNumber(),
                carPage.getSize(),
                carPage.getTotalElements(),
                carPage.getTotalPages(),
                carPage.isLast()
        );
    }

    @Override
    @Transactional
    public CarRentalResponse update(Long id, CarRentalRequest request) {
        CarRental entity = findCarById(id);
        mapToEntity(request, entity);
        return toCarResponse(carRentalRepository.save(entity), 0);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        carRentalRepository.delete(findCarById(id));
    }

    @Override
    public List<CarRentalResponse> findNearby(double lat, double lon, double radiusKm, String sortBy) {
        return carRentalRepository.findByAvailableTrue().stream()
                .map(c -> toCarResponse(c, GeoUtils.calculateDistanceKm(lat, lon, c.getPickupLat(), c.getPickupLon())))
                .filter(dto -> dto.getDistanceKm() <= radiusKm)
                .sorted(getCarComparator(sortBy))
                .toList();
    }

    @Override
    public List<CarRentalResponse> search(String keyword) {
        return carRentalRepository.findByAvailableTrue().stream()
                .filter(c -> c.getModel().toLowerCase().contains(keyword.toLowerCase())
                        || c.getBrand() != null && c.getBrand().toLowerCase().contains(keyword.toLowerCase())
                        || c.getProvider() != null && c.getProvider().toLowerCase().contains(keyword.toLowerCase()))
                .map(c -> toCarResponse(c, 0))
                .toList();
    }

    @Override
    public List<CarRentalResponse> findAvailable(double lat, double lon, CarType type, Double maxPrice, String sortBy) {
        List<CarRental> source;
        if (type != null && maxPrice != null) {
            source = carRentalRepository.findAvailableFiltered(type, maxPrice);
        } else if (type != null) {
            source = carRentalRepository.findByCarTypeAndAvailableTrue(type);
        } else if (maxPrice != null) {
            source = carRentalRepository.findAvailableByMaxPrice(maxPrice);
        } else {
            source = carRentalRepository.findByAvailableTrue();
        }

        return source.stream()
                .map(c -> toCarResponse(c, GeoUtils.calculateDistanceKm(lat, lon, c.getPickupLat(), c.getPickupLon())))
                .sorted(getCarComparator(sortBy))
                .toList();
    }

    // ========== Booking Operations ==========

    @Override
    @Transactional
    public BookingResponse createBooking(BookingRequest request) {
        CarRental car = findCarById(request.getCarId());
        if (!car.isAvailable()) {
            throw new BadRequestException("Car is not available for booking");
        }
        if (request.getDropTime().isBefore(request.getPickupTime())) {
            throw new BadRequestException("Drop time must be after pickup time");
        }

        long hours = Math.max(1, Duration.between(request.getPickupTime(), request.getDropTime()).toHours());
        double totalCost = hours * car.getPricePerHour();

        Booking booking = new Booking();
        booking.setCarId(car.getId());
        booking.setUserId(request.getUserId());
        booking.setPickupTime(request.getPickupTime());
        booking.setDropTime(request.getDropTime());
        booking.setTotalCost(totalCost);
        booking.setStatus(BookingStatus.CONFIRMED);
        booking.setPickupLocation(request.getPickupLocation());
        booking.setDropLocation(request.getDropLocation());

        booking = bookingRepository.save(booking);

        car.setAvailable(false);
        carRentalRepository.save(car);

        return toBookingResponse(booking);
    }

    @Override
    public BookingResponse getBooking(Long bookingId) {
        return toBookingResponse(findBookingById(bookingId));
    }

    @Override
    @Transactional
    public BookingResponse cancelBooking(Long bookingId) {
        Booking booking = findBookingById(bookingId);
        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new BadRequestException("Booking is already cancelled");
        }
        if (booking.getStatus() == BookingStatus.COMPLETED) {
            throw new BadRequestException("Cannot cancel a completed booking");
        }

        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);

        // Make car available again
        CarRental car = findCarById(booking.getCarId());
        car.setAvailable(true);
        carRentalRepository.save(car);

        return toBookingResponse(booking);
    }

    @Override
    public List<BookingResponse> getUserBookings(Long userId) {
        return bookingRepository.findByUserId(userId).stream()
                .map(this::toBookingResponse)
                .toList();
    }

    @Override
    public PagedResponse<BookingResponse> getUserBookingsPaged(Long userId, int page, int size) {
        Page<Booking> bookingPage = bookingRepository.findByUserId(
                userId, PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"))
        );
        return new PagedResponse<>(
                bookingPage.getContent().stream().map(this::toBookingResponse).toList(),
                bookingPage.getNumber(),
                bookingPage.getSize(),
                bookingPage.getTotalElements(),
                bookingPage.getTotalPages(),
                bookingPage.isLast()
        );
    }

    // ========== Private Helpers ==========

    private CarRental findCarById(Long id) {
        return carRentalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CarRental", id));
    }

    private Booking findBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking", id));
    }

    private CarRental mapToEntity(CarRentalRequest request, CarRental entity) {
        entity.setCarType(request.getCarType());
        entity.setModel(request.getModel());
        entity.setBrand(request.getBrand());
        entity.setPricePerHour(request.getPricePerHour());
        entity.setPickupLat(request.getPickupLat());
        entity.setPickupLon(request.getPickupLon());
        entity.setPickupAddress(request.getPickupAddress());
        entity.setProvider(request.getProvider());
        entity.setRating(request.getRating());
        entity.setSeats(request.getSeats());
        entity.setFuelType(request.getFuelType());
        entity.setAutomaticTransmission(request.isAutomaticTransmission());
        return entity;
    }

    private CarRentalResponse toCarResponse(CarRental entity, double distanceKm) {
        CarRentalResponse dto = new CarRentalResponse();
        dto.setId(entity.getId());
        dto.setCarType(entity.getCarType());
        dto.setModel(entity.getModel());
        dto.setBrand(entity.getBrand());
        dto.setPricePerHour(entity.getPricePerHour());
        dto.setAvailable(entity.isAvailable());
        dto.setPickupLat(entity.getPickupLat());
        dto.setPickupLon(entity.getPickupLon());
        dto.setPickupAddress(entity.getPickupAddress());
        dto.setProvider(entity.getProvider());
        dto.setRating(entity.getRating());
        dto.setSeats(entity.getSeats());
        dto.setFuelType(entity.getFuelType());
        dto.setAutomaticTransmission(entity.isAutomaticTransmission());
        dto.setDistanceKm(distanceKm);
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    private BookingResponse toBookingResponse(Booking booking) {
        BookingResponse dto = new BookingResponse();
        dto.setId(booking.getId());
        dto.setCarId(booking.getCarId());
        dto.setUserId(booking.getUserId());
        dto.setPickupTime(booking.getPickupTime());
        dto.setDropTime(booking.getDropTime());
        dto.setTotalCost(booking.getTotalCost());
        dto.setStatus(booking.getStatus());
        dto.setPickupLocation(booking.getPickupLocation());
        dto.setDropLocation(booking.getDropLocation());
        dto.setCreatedAt(booking.getCreatedAt());
        return dto;
    }

    private Comparator<CarRentalResponse> getCarComparator(String sortBy) {
        return switch (sortBy != null ? sortBy.toLowerCase() : "distance") {
            case "rating" -> Comparator.comparingDouble(CarRentalResponse::getRating).reversed();
            case "price" -> Comparator.comparingDouble(CarRentalResponse::getPricePerHour);
            case "name" -> Comparator.comparing(CarRentalResponse::getModel);
            default -> Comparator.comparingDouble(CarRentalResponse::getDistanceKm);
        };
    }
}
