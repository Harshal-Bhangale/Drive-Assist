package com.codewithharshal.driveassist.service;

import com.codewithharshal.driveassist.model.BaseService;
import com.codewithharshal.driveassist.model.CarRental;
import com.codewithharshal.driveassist.model.FuelStation;
import com.codewithharshal.driveassist.model.Garage;
import com.codewithharshal.driveassist.repository.CarRentalRepository;
import com.codewithharshal.driveassist.repository.FuelStationRepository;
import com.codewithharshal.driveassist.repository.GarageRepository;
import com.codewithharshal.driveassist.utils.DistanceCalculator;
//import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Core logic for nearby services
//@Service
@org.springframework.stereotype.Service
/*
Errors -
incompatible types: com.codewithharshal.driveassist.model.FuelStation cannot be converted to org.springframework.stereotype.Service,
java: incompatible types: com.codewithharshal.driveassist.model.Garage cannot be converted to org.springframework.stereotype.Service,
java: incompatible types: com.codewithharshal.driveassist.model.CarRental cannot be converted to org.springframework.stereotype.Service

    These errors are common when using the word @Service in Java, because Spring has @Service annotation.
    Problem -
        @Service
        public class ServiceService { ... }

    Your class is named ServiceService, which is fine.
    But the entities are named Service, and you also imported - org.springframework.stereotype.Service.
    When you try to return a List<Service>, Java thinks you mean Spring's @Service, not your entity Service.
    That's why we get the errors -
        incompatible types: FuelStation cannot be converted to org.springframework.stereotype.Service

    Solutions -
        1. Fully qualify the entity
            @org.springframework.stereotype.Service
            public class ServiceService {
            }
        2. Rename entity
            Instead of calling your entity Service, you could rename it to BaseService or GenericService.
            Then ServiceService will work perfectly without confusion with Spring’s @Service.
                public abstract class BaseService { ... }
                public class ServiceService { ... }
    Key takeaway:
        Never use the same class name as Spring annotations (Service, Component, Controller) for entities.
        Either fully qualify the entity or rename it.
 */
public class ServiceService {

    private final FuelStationRepository fuelRepo;
    private final GarageRepository garageRepo;
    private final CarRentalRepository rentalRepo;

    public ServiceService(FuelStationRepository fuelRepo,
                          GarageRepository garageRepo,
                          CarRentalRepository rentalRepo) {
        this.fuelRepo = fuelRepo;
        this.garageRepo = garageRepo;
        this.rentalRepo = rentalRepo;
    }

    // Get all nearby services within radiusKm
    public List<BaseService> getNearbyServices(double userLat, double userLon, double radiusKm) {
        List<BaseService> nearby = new ArrayList<>();

        // Fuel stations
        for (FuelStation fs : fuelRepo.findAll()) {
            double distance = DistanceCalculator.distance(userLat, userLon, fs.getLatitude(), fs.getLongitude());
            if (distance <= radiusKm) {
                nearby.add(fs);
            }
        }

        // Garages
        for (Garage g : garageRepo.findAll()) {
            double distance = DistanceCalculator.distance(userLat, userLon, g.getLatitude(), g.getLongitude());
            if (distance <= radiusKm) {
                nearby.add(g);
            }
        }

        // Car rentals
        for (CarRental cr : rentalRepo.findAll()) {
            double distance = DistanceCalculator.distance(userLat, userLon, cr.getLatitude(), cr.getLongitude());
            if (distance <= radiusKm) {
                nearby.add(cr);
            }
        }

        return nearby;
    }
}
