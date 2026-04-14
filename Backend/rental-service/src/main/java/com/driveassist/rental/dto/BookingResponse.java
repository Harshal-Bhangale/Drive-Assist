package com.driveassist.rental.dto;

import com.driveassist.rental.enums.BookingStatus;
import java.time.Instant;

public class BookingResponse {

    private Long id;
    private Long carId;
    private Long userId;
    private Instant pickupTime;
    private Instant dropTime;
    private double totalCost;
    private BookingStatus status;
    private String pickupLocation;
    private String dropLocation;
    private Instant createdAt;

    // --- Getters & Setters ---

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Long getCarId() { return carId; }

    public void setCarId(Long carId) { this.carId = carId; }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }

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

    public Instant getCreatedAt() { return createdAt; }

    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
