package com.driveassist.rental.dto;

import jakarta.validation.constraints.NotNull;
import java.time.Instant;

public class BookingRequest {

    @NotNull(message = "Car ID is required")
    private Long carId;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Pickup time is required")
    private Instant pickupTime;

    @NotNull(message = "Drop time is required")
    private Instant dropTime;

    private String pickupLocation;
    private String dropLocation;

    // --- Getters & Setters ---

    public Long getCarId() { return carId; }

    public void setCarId(Long carId) { this.carId = carId; }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }

    public Instant getPickupTime() { return pickupTime; }

    public void setPickupTime(Instant pickupTime) { this.pickupTime = pickupTime; }

    public Instant getDropTime() { return dropTime; }

    public void setDropTime(Instant dropTime) { this.dropTime = dropTime; }

    public String getPickupLocation() { return pickupLocation; }

    public void setPickupLocation(String pickupLocation) { this.pickupLocation = pickupLocation; }

    public String getDropLocation() { return dropLocation; }

    public void setDropLocation(String dropLocation) { this.dropLocation = dropLocation; }
}
