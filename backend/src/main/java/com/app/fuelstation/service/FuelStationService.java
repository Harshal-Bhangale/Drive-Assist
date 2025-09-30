package com.app.fuelstation.service;

import com.app.fuelstation.dto.FuelStationDTO;
import com.app.fuelstation.model.FuelStation;
import com.app.fuelstation.repository.FuelStationRepository;
import com.app.util.GeoUtils;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuelStationService {
    private final FuelStationRepository repository;

    public FuelStationService(FuelStationRepository repository) { this.repository = repository; }

    public List<FuelStationDTO> getNearby(double lat, double lon, String type, String sortBy) {
        List<FuelStation> source = (type == null || type.isBlank()) ? repository.findAll() : repository.findByTypeIgnoreCase(type);
        List<FuelStationDTO> mapped = source.stream().map(fs -> {
            FuelStationDTO dto = new FuelStationDTO();
            dto.setId(fs.getId());
            dto.setName(fs.getName());
            dto.setType(fs.getType());
            dto.setAddress(fs.getAddress());
            dto.setRating(fs.getRating());
            dto.setDistanceKm(GeoUtils.calculateDistanceKm(lat, lon, fs.getLatitude(), fs.getLongitude()));
            return dto;
        }).collect(Collectors.toList());

        if ("rating".equalsIgnoreCase(sortBy)) {
            mapped.sort(Comparator.comparingDouble(FuelStationDTO::getRating).reversed());
        } else {
            mapped.sort(Comparator.comparingDouble(FuelStationDTO::getDistanceKm));
        }
        return mapped;
    }
}



