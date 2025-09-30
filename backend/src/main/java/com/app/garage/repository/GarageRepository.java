package com.app.garage.repository;

import com.app.garage.model.Garage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GarageRepository extends JpaRepository<Garage, Long> {
    List<Garage> findByServicesOfferedContainingIgnoreCase(String service);
}



