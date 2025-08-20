package com.codewithharshal.driveassist.repository;

import com.codewithharshal.driveassist.model.CarRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRentalRepository extends JpaRepository<CarRental, Long> {

    // Example: List<CarRental> findByAvailableTrue();

    // Below this gives errors - don't know why.
//    // Find rentals by car type (SUV, Sedan, etc.)
//    List<CarRental> findByCarType(String carType);
//
//    // Nearby filter
//    List<CarRental> findByLatitudeBetweenAndLongitudeBetween(
//            Double minLat, Double maxLat,
//            Double minLon, Double maxLon
//    );
}