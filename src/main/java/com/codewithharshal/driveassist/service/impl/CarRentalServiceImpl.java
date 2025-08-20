package com.codewithharshal.driveassist.service.impl;

import com.codewithharshal.driveassist.model.CarRental;
import com.codewithharshal.driveassist.repository.CarRentalRepository;
import com.codewithharshal.driveassist.service.CarRentalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarRentalServiceImpl implements CarRentalService {

    private final CarRentalRepository repository;

    public CarRentalServiceImpl(CarRentalRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CarRental> getAll() {
        return repository.findAll();
    }

    @Override
    public CarRental getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public CarRental save(CarRental entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public CarRental findAvailableCar(String carType, Double userLat, Double userLon) {
        return repository.findAll().stream()
                .filter(car -> car.isAvailable() && car.getCarType().equalsIgnoreCase(carType))
                .min((a, b) -> Double.compare(
                        a.distanceKmTo(userLat, userLon),
                        b.distanceKmTo(userLat, userLon)
                ))
                .orElse(null);
    }
}