package com.app.rental.service;

import com.app.rental.dto.BookingConfirmation;
import com.app.rental.dto.BookingRequest;
import com.app.rental.dto.CarRentalDTO;
import com.app.rental.model.Booking;
import com.app.rental.model.CarRental;
import com.app.rental.repository.BookingRepository;
import com.app.rental.repository.CarRentalRepository;
import com.app.util.GeoUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarRentalService {
    private final CarRentalRepository carRentalRepository;
    private final BookingRepository bookingRepository;

    public CarRentalService(CarRentalRepository carRentalRepository, BookingRepository bookingRepository) {
        this.carRentalRepository = carRentalRepository;
        this.bookingRepository = bookingRepository;
    }

    public List<CarRentalDTO> getAvailable(double lat, double lon, String type, Double maxPrice, String sortBy) {
        List<CarRental> source = (type == null || type.isBlank())
                ? carRentalRepository.findByAvailabilityTrue()
                : carRentalRepository.findByCarTypeIgnoreCaseAndAvailabilityTrue(type);

        List<CarRentalDTO> mapped = source.stream().filter(c -> maxPrice == null || c.getPricePerHour() <= maxPrice)
                .map(c -> {
                    CarRentalDTO dto = new CarRentalDTO();
                    dto.setId(c.getId());
                    dto.setCarType(c.getCarType());
                    dto.setModel(c.getModel());
                    dto.setPricePerHour(c.getPricePerHour());
                    dto.setRating(c.getRating());
                    dto.setDistanceKm(GeoUtils.calculateDistanceKm(lat, lon, c.getPickupLat(), c.getPickupLon()));
                    return dto;
                }).collect(Collectors.toList());

        if ("rating".equalsIgnoreCase(sortBy)) {
            mapped.sort(Comparator.comparingDouble(CarRentalDTO::getRating).reversed());
        } else if ("price".equalsIgnoreCase(sortBy)) {
            mapped.sort(Comparator.comparingDouble(CarRentalDTO::getPricePerHour));
        } else {
            mapped.sort(Comparator.comparingDouble(CarRentalDTO::getDistanceKm));
        }
        return mapped;
    }

    @Transactional
    public BookingConfirmation book(BookingRequest request) {
        CarRental car = carRentalRepository.findById(request.getCarId()).orElseThrow();
        if (!car.isAvailability()) {
            throw new IllegalStateException("Car not available");
        }
        long hours = Math.max(1, Duration.between(request.getPickupTime(), request.getDropTime()).toHours());
        double cost = hours * car.getPricePerHour();

        Booking booking = new Booking();
        booking.setCarId(car.getId());
        booking.setUserId(request.getUserId());
        booking.setPickupTime(request.getPickupTime());
        booking.setDropTime(request.getDropTime());
        booking.setTotalCost(cost);
        booking.setStatus("CONFIRMED");
        booking = bookingRepository.save(booking);

        car.setAvailability(false);
        carRentalRepository.save(car);

        BookingConfirmation conf = new BookingConfirmation();
        conf.setBookingId(booking.getBookingId());
        conf.setCarId(booking.getCarId());
        conf.setUserId(booking.getUserId());
        conf.setTotalCost(booking.getTotalCost());
        conf.setStatus(booking.getStatus());
        return conf;
    }
}



