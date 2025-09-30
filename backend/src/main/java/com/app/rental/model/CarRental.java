package com.app.rental.model;

import jakarta.persistence.*;

@Entity
@Table(name = "car_rentals", indexes = {
        @Index(name = "idx_rental_available", columnList = "availability"),
        @Index(name = "idx_rental_lat", columnList = "pickupLat"),
        @Index(name = "idx_rental_lon", columnList = "pickupLon")
})
public class CarRental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String carType;
    private String model;
    private double pricePerHour;
    private boolean availability;
    private double pickupLat;
    private double pickupLon;
    private String provider;
    private double rating;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCarType() { return carType; }
    public void setCarType(String carType) { this.carType = carType; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public double getPricePerHour() { return pricePerHour; }
    public void setPricePerHour(double pricePerHour) { this.pricePerHour = pricePerHour; }
    public boolean isAvailability() { return availability; }
    public void setAvailability(boolean availability) { this.availability = availability; }
    public double getPickupLat() { return pickupLat; }
    public void setPickupLat(double pickupLat) { this.pickupLat = pickupLat; }
    public double getPickupLon() { return pickupLon; }
    public void setPickupLon(double pickupLon) { this.pickupLon = pickupLon; }
    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
}



