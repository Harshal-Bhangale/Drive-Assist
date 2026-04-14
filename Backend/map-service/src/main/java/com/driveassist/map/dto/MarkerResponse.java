package com.driveassist.map.dto;

/**
 * Represents a single point-of-interest marker on the map.
 */
public class MarkerResponse {

    private String type; // FUEL_STATION, GARAGE, RENTAL_CAR
    private String name;
    private double lat;
    private double lon;
    private String address;
    private double distanceKm;
    private double rating;
    private Object details; // full service-specific payload

    public MarkerResponse() {}

    public MarkerResponse(String type, String name, double lat, double lon,
                          String address, double distanceKm, double rating, Object details) {
        this.type = type;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.address = address;
        this.distanceKm = distanceKm;
        this.rating = rating;
        this.details = details;
    }

    // --- Getters & Setters ---

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public double getLat() { return lat; }

    public void setLat(double lat) { this.lat = lat; }

    public double getLon() { return lon; }

    public void setLon(double lon) { this.lon = lon; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public double getDistanceKm() { return distanceKm; }

    public void setDistanceKm(double distanceKm) { this.distanceKm = distanceKm; }

    public double getRating() { return rating; }

    public void setRating(double rating) { this.rating = rating; }

    public Object getDetails() { return details; }

    public void setDetails(Object details) { this.details = details; }
}
