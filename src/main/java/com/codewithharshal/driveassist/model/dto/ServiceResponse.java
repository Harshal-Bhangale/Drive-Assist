package com.codewithharshal.driveassist.model.dto;

import com.codewithharshal.driveassist.model.enums.ServiceType;

public class ServiceResponse {

    private Long id;
    private ServiceType type;
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private Double distanceKm;
    private Double rating;

    public ServiceResponse(Long id, ServiceType type, String name, String address,
                           Double latitude, Double longitude, Double distanceKm, Double rating) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distanceKm = distanceKm;
        this.rating = rating;
    }

    // Getters & setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public ServiceType getType() { return type; }
    public void setType(ServiceType type) { this.type = type; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public Double getDistanceKm() { return distanceKm; }
    public void setDistanceKm(Double distanceKm) { this.distanceKm = distanceKm; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }
}
