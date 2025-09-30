package com.app.garage.service;

import com.app.garage.dto.GarageDTO;
import com.app.garage.model.Garage;
import com.app.garage.repository.GarageRepository;
import com.app.util.GeoUtils;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GarageService {
    private final GarageRepository repository;

    public GarageService(GarageRepository repository) { this.repository = repository; }

    public List<GarageDTO> getNearby(double lat, double lon, String service, String sortBy) {
        List<Garage> source = (service == null || service.isBlank())
                ? repository.findAll()
                : repository.findByServicesOfferedContainingIgnoreCase(service);

        List<GarageDTO> mapped = source.stream().map(g -> {
            GarageDTO dto = new GarageDTO();
            dto.setId(g.getId());
            dto.setName(g.getName());
            dto.setServicesOffered(g.getServicesOffered());
            dto.setAddress(g.getAddress());
            dto.setRating(g.getRating());
            dto.setDistanceKm(GeoUtils.calculateDistanceKm(lat, lon, g.getLatitude(), g.getLongitude()));
            return dto;
        }).collect(Collectors.toList());

        if ("rating".equalsIgnoreCase(sortBy)) {
            mapped.sort(Comparator.comparingDouble(GarageDTO::getRating).reversed());
        } else {
            mapped.sort(Comparator.comparingDouble(GarageDTO::getDistanceKm));
        }
        return mapped;
    }
}



