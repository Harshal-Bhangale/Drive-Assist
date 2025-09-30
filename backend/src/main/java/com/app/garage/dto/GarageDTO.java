package com.app.garage.dto;

public class GarageDTO {
    private Long id;
    private String name;
    private String servicesOffered;
    private String address;
    private double rating;
    private double distanceKm;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getServicesOffered() { return servicesOffered; }
    public void setServicesOffered(String servicesOffered) { this.servicesOffered = servicesOffered; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
    public double getDistanceKm() { return distanceKm; }
    public void setDistanceKm(double distanceKm) { this.distanceKm = distanceKm; }
}



