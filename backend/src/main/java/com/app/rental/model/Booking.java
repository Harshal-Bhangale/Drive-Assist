package com.app.rental.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    private Long userId;
    private Long carId;
    private Instant pickupTime;
    private Instant dropTime;
    private double totalCost;
    private String status; // CONFIRMED, CANCELLED

    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }
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
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}



