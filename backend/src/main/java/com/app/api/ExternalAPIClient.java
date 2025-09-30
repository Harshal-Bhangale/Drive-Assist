package com.app.api;

import java.util.List;

public interface ExternalAPIClient {
    record DirectionsDTO(double distanceKm, double durationMinutes, String polyline, List<String> instructions) {}

    DirectionsDTO getDirections(double startLat, double startLon, double endLat, double endLon);
}



