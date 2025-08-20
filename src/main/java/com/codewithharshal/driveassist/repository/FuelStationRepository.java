package com.codewithharshal.driveassist.repository;

import com.codewithharshal.driveassist.model.FuelStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuelStationRepository extends JpaRepository<FuelStation, Long> {

    // Custom query example (optional for now)
    // List<FuelStation> findByCity(String city);

    // Below this gives errors - don't know why.
//    // Custom query to find stations by fuel type
//    List<FuelStation> findByFuelType(String fuelType);
//
//    // Custom query to find nearby (later we’ll refine with distance logic)
//    List<FuelStation> findByLatitudeBetweenAndLongitudeBetween(
//            Double minLat, Double maxLat,
//            Double minLon, Double maxLon
//    );
}
