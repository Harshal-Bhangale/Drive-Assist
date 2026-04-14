package com.driveassist.map.dto;

import java.util.List;

/**
 * Aggregated map response — contains markers from all services.
 */
public class MapResponse {

    private double centerLat;
    private double centerLon;
    private double radiusKm;
    private int totalMarkers;
    private List<MarkerResponse> fuelStations;
    private List<MarkerResponse> garages;
    private List<MarkerResponse> rentalCars;

    // --- Getters & Setters ---

    public double getCenterLat() { return centerLat; }

    public void setCenterLat(double centerLat) { this.centerLat = centerLat; }

    public double getCenterLon() { return centerLon; }

    public void setCenterLon(double centerLon) { this.centerLon = centerLon; }

    public double getRadiusKm() { return radiusKm; }

    public void setRadiusKm(double radiusKm) { this.radiusKm = radiusKm; }

    public int getTotalMarkers() { return totalMarkers; }

    public void setTotalMarkers(int totalMarkers) { this.totalMarkers = totalMarkers; }

    public List<MarkerResponse> getFuelStations() { return fuelStations; }

    public void setFuelStations(List<MarkerResponse> fuelStations) { this.fuelStations = fuelStations; }

    public List<MarkerResponse> getGarages() { return garages; }

    public void setGarages(List<MarkerResponse> garages) { this.garages = garages; }

    public List<MarkerResponse> getRentalCars() { return rentalCars; }

    public void setRentalCars(List<MarkerResponse> rentalCars) { this.rentalCars = rentalCars; }
}
