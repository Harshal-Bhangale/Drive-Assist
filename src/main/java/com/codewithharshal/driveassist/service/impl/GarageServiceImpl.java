package com.codewithharshal.driveassist.service.impl;

import com.codewithharshal.driveassist.model.Garage;
import com.codewithharshal.driveassist.repository.GarageRepository;
import com.codewithharshal.driveassist.service.GarageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GarageServiceImpl implements GarageService {

    private final GarageRepository repository;

    public GarageServiceImpl(GarageRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Garage> getAll() {
        return repository.findAll();
    }

    @Override
    public Garage getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Garage save(Garage entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Garage findNearest(Double userLat, Double userLon) {
        return repository.findAll().stream()
                .min((a, b) -> Double.compare(
                        a.distanceKmTo(userLat, userLon),
                        b.distanceKmTo(userLat, userLon)
                ))
                .orElse(null);
    }
}