package com.app.map.dto;

import java.util.List;

public class RouteDTO {
    private double distanceKm;
    private double durationMinutes;
    private String polyline;
    private List<String> instructions;

    public double getDistanceKm() { return distanceKm; }
    public void setDistanceKm(double distanceKm) { this.distanceKm = distanceKm; }
    public double getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(double durationMinutes) { this.durationMinutes = durationMinutes; }
    public String getPolyline() { return polyline; }
    public void setPolyline(String polyline) { this.polyline = polyline; }
    public List<String> getInstructions() { return instructions; }
    public void setInstructions(List<String> instructions) { this.instructions = instructions; }
}



