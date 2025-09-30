package com.app.fuelstation.dto;

public class FuelStationDTO {
    private Long id;
    private String name;
    private String type;
    private String address;
    private double rating;
    private double distanceKm;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
    public double getDistanceKm() { return distanceKm; }
    public void setDistanceKm(double distanceKm) { this.distanceKm = distanceKm; }
}



