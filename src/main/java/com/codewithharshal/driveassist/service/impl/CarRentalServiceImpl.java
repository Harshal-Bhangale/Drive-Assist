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

    /**
     * Finds the nearest available car of a specific type for the user’s location.
     *
     * @param carType  Type of car (e.g., SUV, Sedan)
     * @param userLat  User’s latitude
     * @param userLon  User’s longitude
     * @return nearest available CarRental or null if none found
     */
    @Override
    public CarRental findAvailableCar(String carType, Double userLat, Double userLon) {
        return repository.findAll().stream()
                .filter(CarRental::isAvailable) // only available cars
                .filter(car -> carType == null || carType.isEmpty() || carType.equalsIgnoreCase(car.getCarType()))
                .min((a, b) -> {
                    double distA = Math.pow(a.getLatitude() - userLat, 2) + Math.pow(a.getLongitude() - userLon, 2);
                    double distB = Math.pow(b.getLatitude() - userLat, 2) + Math.pow(b.getLongitude() - userLon, 2);
                    return Double.compare(distA, distB);
                })
                .orElse(null);
    }
}
