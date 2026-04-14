package com.driveassist.rental.dto;

import com.driveassist.rental.enums.CarType;
import java.time.Instant;

public class CarRentalResponse {

    private Long id;
    private CarType carType;
    private String model;
    private String brand;
    private double pricePerHour;
    private boolean available;
    private double pickupLat;
    private double pickupLon;
    private String pickupAddress;
    private String provider;
    private double rating;
    private int seats;
    private String fuelType;
    private boolean automaticTransmission;
    private double distanceKm;
    private Instant createdAt;

    // --- Getters & Setters ---

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

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

    public double getDistanceKm() { return distanceKm; }

    public void setDistanceKm(double distanceKm) { this.distanceKm = distanceKm; }

    public Instant getCreatedAt() { return createdAt; }

    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
