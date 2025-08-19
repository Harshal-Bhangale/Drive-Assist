package com.codewithharshal.driveassist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelStationRepository extends JpaRepository<FuelStationRepository, Long> {
    // You can add custom queries here if needed.
}
