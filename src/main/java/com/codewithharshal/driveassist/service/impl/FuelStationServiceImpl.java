package com.codewithharshal.driveassist.service.impl;

import com.codewithharshal.driveassist.model.FuelStation;
import com.codewithharshal.driveassist.repository.FuelStationRepository;
import com.codewithharshal.driveassist.service.FuelStationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuelStationServiceImpl implements FuelStationService {

    private final FuelStationRepository repository;

    public FuelStationServiceImpl(FuelStationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<FuelStation> getAll() {
        return repository.findAll();
    }

    @Override
    public FuelStation getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public FuelStation save(FuelStation entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public FuelStation findNearest(Double userLat, Double userLon) {
        return repository.findAll().stream()
                .min((a, b) -> Double.compare(
                        a.distanceKmTo(userLat, userLon),
                        b.distanceKmTo(userLat, userLon)
                ))
                .orElse(null);
    }
}