package com.driveassist.rental.repository;

import com.driveassist.rental.entity.CarRental;
import com.driveassist.rental.enums.CarType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRentalRepository extends JpaRepository<CarRental, Long> {

    List<CarRental> findByAvailableTrue();

    List<CarRental> findByCarTypeAndAvailableTrue(CarType carType);

    @Query("SELECT c FROM CarRental c WHERE c.available = true AND c.pricePerHour <= :maxPrice")
    List<CarRental> findAvailableByMaxPrice(@Param("maxPrice") double maxPrice);

    @Query("SELECT c FROM CarRental c WHERE c.available = true " +
           "AND (:carType IS NULL OR c.carType = :carType) " +
           "AND (:maxPrice IS NULL OR c.pricePerHour <= :maxPrice)")
    List<CarRental> findAvailableFiltered(
            @Param("carType") CarType carType,
            @Param("maxPrice") Double maxPrice
    );
}
