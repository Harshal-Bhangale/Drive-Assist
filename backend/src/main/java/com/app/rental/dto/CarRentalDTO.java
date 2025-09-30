package com.app.rental.dto;

public class CarRentalDTO {
    private Long id;
    private String carType;
    private String model;
    private double pricePerHour;
    private double rating;
    private double distanceKm;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCarType() { return carType; }
    public void setCarType(String carType) { this.carType = carType; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public double getPricePerHour() { return pricePerHour; }
    public void setPricePerHour(double pricePerHour) { this.pricePerHour = pricePerHour; }
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
    public double getDistanceKm() { return distanceKm; }
    public void setDistanceKm(double distanceKm) { this.distanceKm = distanceKm; }
}



