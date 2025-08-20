package com.codewithharshal.driveassist.repository;

import com.codewithharshal.driveassist.model.Garage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GarageRepository extends JpaRepository<Garage, Long> {

    // Example: List<Garage> findByCity(String city);

    // Below this gives errors - don't know why.
//    // Custom query to filter by service type (e.g., "Repair", "Towing")
//    List<Garage> findByServiceType(String serviceType);
//
//    // Nearby filter
//    List<Garage> findByLatitudeBetweenAndLongitudeBetween(
//            Double minLat, Double maxLat,
//            Double minLon, Double maxLon
//    );
}
