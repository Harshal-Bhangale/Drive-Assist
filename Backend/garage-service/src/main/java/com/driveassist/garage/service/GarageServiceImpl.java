package com.driveassist.garage.service;

import com.driveassist.common.dto.PagedResponse;
import com.driveassist.common.exception.ResourceNotFoundException;
import com.driveassist.common.util.GeoUtils;
import com.driveassist.garage.dto.GarageRequest;
import com.driveassist.garage.dto.GarageResponse;
import com.driveassist.garage.entity.Garage;
import com.driveassist.garage.enums.GarageSpecialization;
import com.driveassist.garage.repository.GarageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
public class GarageServiceImpl implements GarageService {

    private final GarageRepository repository;

    public GarageServiceImpl(GarageRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public GarageResponse create(GarageRequest request) {
        return toResponse(repository.save(mapToEntity(request, new Garage())), 0);
    }

    @Override
    public GarageResponse getById(Long id) {
        return toResponse(findById(id), 0);
    }

    @Override
    public PagedResponse<GarageResponse> getAll(int page, int size, String sortBy) {
        Page<Garage> garagePage = repository.findAll(
                PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, sortBy))
        );
        return new PagedResponse<>(
                garagePage.getContent().stream().map(g -> toResponse(g, 0)).toList(),
                garagePage.getNumber(),
                garagePage.getSize(),
                garagePage.getTotalElements(),
                garagePage.getTotalPages(),
                garagePage.isLast()
        );
    }

    @Override
    @Transactional
    public GarageResponse update(Long id, GarageRequest request) {
        Garage entity = findById(id);
        mapToEntity(request, entity);
        return toResponse(repository.save(entity), 0);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.delete(findById(id));
    }

    @Override
    public List<GarageResponse> findNearby(double lat, double lon, double radiusKm, String sortBy) {
        List<GarageResponse> results = repository.findAll().stream()
                .map(g -> toResponse(g, GeoUtils.calculateDistanceKm(lat, lon, g.getLatitude(), g.getLongitude())))
                .filter(dto -> dto.getDistanceKm() <= radiusKm)
                .toList();

        return sortResults(results, sortBy);
    }

    @Override
    public List<GarageResponse> search(String keyword) {
        return repository.searchByKeyword(keyword).stream()
                .map(g -> toResponse(g, 0))
                .toList();
    }

    @Override
    public List<GarageResponse> findBySpecialization(GarageSpecialization specialization) {
        return repository.findBySpecialization(specialization).stream()
                .map(g -> toResponse(g, 0))
                .toList();
    }

    @Override
    public List<GarageResponse> findEmergencyGarages() {
        return repository.findByEmergencyServiceTrue().stream()
                .map(g -> toResponse(g, 0))
                .toList();
    }

    // --- Private helpers ---

    private Garage findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Garage", id));
    }

    private Garage mapToEntity(GarageRequest request, Garage entity) {
        entity.setName(request.getName());
        entity.setSpecialization(request.getSpecialization());
        entity.setServicesOffered(request.getServicesOffered());
        entity.setAddress(request.getAddress());
        entity.setCity(request.getCity());
        entity.setState(request.getState());
        entity.setLatitude(request.getLatitude());
        entity.setLongitude(request.getLongitude());
        entity.setContact(request.getContact());
        entity.setRating(request.getRating());
        entity.setEmergencyService(request.isEmergencyService());
        return entity;
    }

    private GarageResponse toResponse(Garage entity, double distanceKm) {
        GarageResponse dto = new GarageResponse();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSpecialization(entity.getSpecialization());
        dto.setServicesOffered(entity.getServicesOffered());
        dto.setAddress(entity.getAddress());
        dto.setCity(entity.getCity());
        dto.setState(entity.getState());
        dto.setLatitude(entity.getLatitude());
        dto.setLongitude(entity.getLongitude());
        dto.setContact(entity.getContact());
        dto.setRating(entity.getRating());
        dto.setEmergencyService(entity.isEmergencyService());
        dto.setDistanceKm(distanceKm);
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }

    private List<GarageResponse> sortResults(List<GarageResponse> results, String sortBy) {
        Comparator<GarageResponse> comparator = switch (sortBy != null ? sortBy.toLowerCase() : "distance") {
            case "rating" -> Comparator.comparingDouble(GarageResponse::getRating).reversed();
            case "name" -> Comparator.comparing(GarageResponse::getName);
            default -> Comparator.comparingDouble(GarageResponse::getDistanceKm);
        };
        return results.stream().sorted(comparator).toList();
    }
}
