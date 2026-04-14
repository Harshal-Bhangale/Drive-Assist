package com.driveassist.fuelstation.service;

import com.driveassist.common.dto.PagedResponse;
import com.driveassist.common.exception.ResourceNotFoundException;
import com.driveassist.common.util.GeoUtils;
import com.driveassist.fuelstation.dto.FuelStationRequest;
import com.driveassist.fuelstation.dto.FuelStationResponse;
import com.driveassist.fuelstation.entity.FuelStation;
import com.driveassist.fuelstation.enums.FuelType;
import com.driveassist.fuelstation.repository.FuelStationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

/**
 * Concrete implementation — OOP Polymorphism via LocationAwareService.
 */
@Service
public class FuelStationServiceImpl implements FuelStationService {

    private final FuelStationRepository repository;

    public FuelStationServiceImpl(FuelStationRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public FuelStationResponse create(FuelStationRequest request) {
        FuelStation entity = mapToEntity(request, new FuelStation());
        return toResponse(repository.save(entity), 0);
    }

    @Override
    public FuelStationResponse getById(Long id) {
        return toResponse(findById(id), 0);
    }

    @Override
    public PagedResponse<FuelStationResponse> getAll(int page, int size, String sortBy) {
        Page<FuelStation> stationPage = repository.findAll(
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sortBy))
        );
        return new PagedResponse<>(
                stationPage.getContent().stream().map(fs -> toResponse(fs, 0)).toList(),
                stationPage.getNumber(),
                stationPage.getSize(),
                stationPage.getTotalElements(),
                stationPage.getTotalPages(),
                stationPage.isLast()
        );
    }

    @Override
    @Transactional
    public FuelStationResponse update(Long id, FuelStationRequest request) {
        FuelStation entity = findById(id);
        mapToEntity(request, entity);
        return toResponse(repository.save(entity), 0);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        FuelStation entity = findById(id);
        repository.delete(entity);
    }

    @Override
    public List<FuelStationResponse> findNearby(double lat, double lon, double radiusKm, String sortBy) {
        List<FuelStationResponse> results = repository.findAll().stream()
                .map(fs -> toResponse(fs, GeoUtils.calculateDistanceKm(lat, lon, fs.getLatitude(), fs.getLongitude())))
                .filter(dto -> dto.getDistanceKm() <= radiusKm)
                .toList();

        return sortResults(results, sortBy);
    }

    @Override
    public List<FuelStationResponse> search(String keyword) {
        return repository.searchByKeyword(keyword).stream()
                .map(fs -> toResponse(fs, 0))
                .toList();
    }

    @Override
    public List<FuelStationResponse> findByType(FuelType fuelType) {
        return repository.findByFuelType(fuelType).stream()
                .map(fs -> toResponse(fs, 0))
                .toList();
    }

    @Override
    public List<FuelStationResponse> findOpen24Hours() {
        return repository.findByOpen24HoursTrue().stream()
                .map(fs -> toResponse(fs, 0))
                .toList();
    }

    // --- Private helpers ---

    private FuelStation findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FuelStation", id));
    }

    private FuelStation mapToEntity(FuelStationRequest request, FuelStation entity) {
        entity.setName(request.getName());
        entity.setFuelType(request.getFuelType());
        entity.setAddress(request.getAddress());
        entity.setCity(request.getCity());
        entity.setState(request.getState());
        entity.setLatitude(request.getLatitude());
        entity.setLongitude(request.getLongitude());
        entity.setContact(request.getContact());
        entity.setRating(request.getRating());
        entity.setOpen24Hours(request.isOpen24Hours());
        return entity;
    }

    private FuelStationResponse toResponse(FuelStation entity, double distanceKm) {
        FuelStationResponse dto = new FuelStationResponse();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setFuelType(entity.getFuelType());
        dto.setAddress(entity.getAddress());
        dto.setCity(entity.getCity());
        dto.setState(entity.getState());
        dto.setLatitude(entity.getLatitude());
        dto.setLongitude(entity.getLongitude());
        dto.setContact(entity.getContact());
        dto.setRating(entity.getRating());
        dto.setOpen24Hours(entity.isOpen24Hours());
        dto.setDistanceKm(distanceKm);
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    private List<FuelStationResponse> sortResults(List<FuelStationResponse> results, String sortBy) {
        Comparator<FuelStationResponse> comparator = switch (sortBy != null ? sortBy.toLowerCase() : "distance") {
            case "rating" -> Comparator.comparingDouble(FuelStationResponse::getRating).reversed();
            case "name" -> Comparator.comparing(FuelStationResponse::getName);
            default -> Comparator.comparingDouble(FuelStationResponse::getDistanceKm);
        };
        return results.stream().sorted(comparator).toList();
    }
}
