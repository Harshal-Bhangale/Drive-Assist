package com.app.rental.repository;

import com.app.rental.model.CarRental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRentalRepository extends JpaRepository<CarRental, Long> {
    List<CarRental> findByAvailabilityTrue();
    List<CarRental> findByCarTypeIgnoreCaseAndAvailabilityTrue(String type);
}



