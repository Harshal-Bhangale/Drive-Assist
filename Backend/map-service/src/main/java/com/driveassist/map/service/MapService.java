package com.driveassist.map.service;

import com.driveassist.map.dto.MapResponse;
import com.driveassist.map.dto.RouteResponse;

/**
 * Map service interface — Abstraction (OOP).
 * Aggregates nearby data from multiple microservices and provides routing.
 */
public interface MapService {

    /**
     * Query all downstream services and aggregate markers within a radius.
     */
    MapResponse getNearbyAll(double lat, double lon, double radiusKm, String sortBy);

    /**
     * Calculate a route between two coordinates.
     */
    RouteResponse getRoute(double originLat, double originLon, double destLat, double destLon);
}
