package com.codewithharshal.driveassist.repository;

import com.codewithharshal.driveassist.model.CarRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRentalRepository extends JpaRepository<CarRental, Long> {
}
