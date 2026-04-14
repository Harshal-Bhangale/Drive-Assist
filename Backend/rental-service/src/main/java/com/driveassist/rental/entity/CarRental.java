package com.driveassist.rental.entity;

import com.driveassist.common.entity.BaseEntity;
import com.driveassist.rental.enums.CarType;
import jakarta.persistence.*;

/**
 * CarRental entity — inherits from BaseEntity (OOP Inheritance).
 */
@Entity
@Table(name = "car_rentals", indexes = {
        @Index(name = "idx_rental_available", columnList = "available"),
        @Index(name = "idx_rental_type", columnList = "carType"),
        @Index(name = "idx_rental_lat", columnList = "pickupLat"),
        @Index(name = "idx_rental_lon", columnList = "pickupLon")
})
public class CarRental extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CarType carType;

    @Column(nullable = false)
    private String model;

    private String brand;

    @Column(nullable = false)
    private double pricePerHour;

    private boolean available = true;

    private double pickupLat;
    private double pickupLon;
    private String pickupAddress;
    private String provider;
    private double rating;
    private int seats;
    private String fuelType;
    private boolean automaticTransmission;

    // --- Getters & Setters ---

    public CarType getCarType() { return carType; }

    public void setCarType(CarType carType) { this.carType = carType; }

    public String getModel() { return model; }

    public void setModel(String model) { this.model = model; }

    public String getBrand() { return brand; }

    public void setBrand(String brand) { this.brand = brand; }

    public double getPricePerHour() { return pricePerHour; }

    public void setPricePerHour(double pricePerHour) { this.pricePerHour = pricePerHour; }

    public boolean isAvailable() { return available; }

    public void setAvailable(boolean available) { this.available = available; }

    public double getPickupLat() { return pickupLat; }

    public void setPickupLat(double pickupLat) { this.pickupLat = pickupLat; }

    public double getPickupLon() { return pickupLon; }

    public void setPickupLon(double pickupLon) { this.pickupLon = pickupLon; }

    public String getPickupAddress() { return pickupAddress; }

    public void setPickupAddress(String pickupAddress) { this.pickupAddress = pickupAddress; }

    public String getProvider() { return provider; }

    public void setProvider(String provider) { this.provider = provider; }

    public double getRating() { return rating; }

    public void setRating(double rating) { this.rating = rating; }

    public int getSeats() { return seats; }

    public void setSeats(int seats) { this.seats = seats; }

    public String getFuelType() { return fuelType; }

    public void setFuelType(String fuelType) { this.fuelType = fuelType; }

    public boolean isAutomaticTransmission() { return automaticTransmission; }

    public void setAutomaticTransmission(boolean automaticTransmission) { this.automaticTransmission = automaticTransmission; }
}
