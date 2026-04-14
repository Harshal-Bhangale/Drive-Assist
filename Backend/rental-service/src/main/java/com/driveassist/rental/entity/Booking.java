package com.driveassist.rental.entity;

import com.driveassist.common.entity.BaseEntity;
import com.driveassist.rental.enums.BookingStatus;
import jakarta.persistence.*;
import java.time.Instant;

/**
 * Booking entity — inherits audit fields from BaseEntity (OOP Inheritance).
 */
@Entity
@Table(name = "bookings", indexes = {
        @Index(name = "idx_booking_user", columnList = "userId"),
        @Index(name = "idx_booking_status", columnList = "status")
})
public class Booking extends BaseEntity {

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long carId;

    @Column(nullable = false)
    private Instant pickupTime;

    @Column(nullable = false)
    private Instant dropTime;

    private double totalCost;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus status = BookingStatus.PENDING;

    private String pickupLocation;
    private String dropLocation;

    // --- Getters & Setters ---

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }

    public Long getCarId() { return carId; }

    public void setCarId(Long carId) { this.carId = carId; }

    public Instant getPickupTime() { return pickupTime; }

    public void setPickupTime(Instant pickupTime) { this.pickupTime = pickupTime; }

    public Instant getDropTime() { return dropTime; }

    public void setDropTime(Instant dropTime) { this.dropTime = dropTime; }

    public double getTotalCost() { return totalCost; }

    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }

    public BookingStatus getStatus() { return status; }

    public void setStatus(BookingStatus status) { this.status = status; }

    public String getPickupLocation() { return pickupLocation; }

    public void setPickupLocation(String pickupLocation) { this.pickupLocation = pickupLocation; }

    public String getDropLocation() { return dropLocation; }

    public void setDropLocation(String dropLocation) { this.dropLocation = dropLocation; }
}
