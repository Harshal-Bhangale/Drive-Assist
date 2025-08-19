package com.codewithharshal.driveassist.repository;

import com.codewithharshal.driveassist.model.FuelStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelStationRepository extends JpaRepository<FuelStation, Long> {
    // You can add custom queries here if needed.
}
