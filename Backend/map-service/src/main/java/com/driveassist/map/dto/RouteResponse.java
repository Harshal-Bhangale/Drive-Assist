package com.driveassist.map.dto;

/**
 * Dummy route response — would be backed by a real directions API in production.
 */
public class RouteResponse {

    private double originLat;
    private double originLon;
    private double destLat;
    private double destLon;
    private double distanceKm;
    private int estimatedMinutes;
    private String summary;

    public RouteResponse() {}

    public RouteResponse(double originLat, double originLon, double destLat, double destLon,
                         double distanceKm, int estimatedMinutes, String summary) {
        this.originLat = originLat;
        this.originLon = originLon;
        this.destLat = destLat;
        this.destLon = destLon;
        this.distanceKm = distanceKm;
        this.estimatedMinutes = estimatedMinutes;
        this.summary = summary;
    }

    // --- Getters & Setters ---

    public double getOriginLat() { return originLat; }

    public void setOriginLat(double originLat) { this.originLat = originLat; }

    public double getOriginLon() { return originLon; }

    public void setOriginLon(double originLon) { this.originLon = originLon; }

    public double getDestLat() { return destLat; }

    public void setDestLat(double destLat) { this.destLat = destLat; }

    public double getDestLon() { return destLon; }

    public void setDestLon(double destLon) { this.destLon = destLon; }

    public double getDistanceKm() { return distanceKm; }

    public void setDistanceKm(double distanceKm) { this.distanceKm = distanceKm; }

    public int getEstimatedMinutes() { return estimatedMinutes; }

    public void setEstimatedMinutes(int estimatedMinutes) { this.estimatedMinutes = estimatedMinutes; }

    public String getSummary() { return summary; }

    public void setSummary(String summary) { this.summary = summary; }
}
