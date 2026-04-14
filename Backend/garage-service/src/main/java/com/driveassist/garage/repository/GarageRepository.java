package com.driveassist.garage.repository;

import com.driveassist.garage.entity.Garage;
import com.driveassist.garage.enums.GarageSpecialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GarageRepository extends JpaRepository<Garage, Long> {

    List<Garage> findBySpecialization(GarageSpecialization specialization);

    List<Garage> findByServicesOfferedContainingIgnoreCase(String service);

    List<Garage> findByEmergencyServiceTrue();

    @Query("SELECT g FROM Garage g WHERE LOWER(g.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "OR LOWER(g.servicesOffered) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "OR LOWER(g.city) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Garage> searchByKeyword(@Param("keyword") String keyword);
}
