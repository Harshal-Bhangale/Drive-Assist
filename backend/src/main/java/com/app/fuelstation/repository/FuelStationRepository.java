package com.app.fuelstation.repository;

import com.app.fuelstation.model.FuelStation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FuelStationRepository extends JpaRepository<FuelStation, Long> {
    List<FuelStation> findByTypeIgnoreCase(String type);
}



