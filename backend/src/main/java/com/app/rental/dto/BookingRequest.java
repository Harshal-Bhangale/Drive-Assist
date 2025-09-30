package com.app.rental.dto;

import jakarta.validation.constraints.NotNull;
import java.time.Instant;

public class BookingRequest {
    @NotNull
    private Long carId;
    @NotNull
    private Long userId;
    @NotNull
    private Instant pickupTime;
    @NotNull
    private Instant dropTime;

    public Long getCarId() { return carId; }
    public void setCarId(Long carId) { this.carId = carId; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Instant getPickupTime() { return pickupTime; }
    public void setPickupTime(Instant pickupTime) { this.pickupTime = pickupTime; }
    public Instant getDropTime() { return dropTime; }
    public void setDropTime(Instant dropTime) { this.dropTime = dropTime; }
}



