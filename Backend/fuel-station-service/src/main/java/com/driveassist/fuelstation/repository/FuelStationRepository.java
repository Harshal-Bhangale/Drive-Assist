package com.driveassist.fuelstation.repository;

import com.driveassist.fuelstation.entity.FuelStation;
import com.driveassist.fuelstation.enums.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FuelStationRepository extends JpaRepository<FuelStation, Long> {

    List<FuelStation> findByFuelType(FuelType fuelType);

    List<FuelStation> findByCity(String city);

    @Query("SELECT fs FROM FuelStation fs WHERE LOWER(fs.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "OR LOWER(fs.address) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "OR LOWER(fs.city) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<FuelStation> searchByKeyword(@Param("keyword") String keyword);

    List<FuelStation> findByOpen24HoursTrue();
}
