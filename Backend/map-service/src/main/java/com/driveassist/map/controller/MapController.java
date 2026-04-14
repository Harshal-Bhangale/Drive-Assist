package com.driveassist.map.controller;

import com.driveassist.common.dto.ApiResponse;
import com.driveassist.map.dto.MapResponse;
import com.driveassist.map.dto.RouteResponse;
import com.driveassist.map.service.MapService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/map")
public class MapController {

    private final MapService mapService;

    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    /**
     * Aggregate nearby fuel-stations, garages, and rental cars in a single response.
     */
    @GetMapping("/nearby")
    public ResponseEntity<ApiResponse<MapResponse>> nearby(
            @RequestParam double lat,
            @RequestParam("lon") double lon,
            @RequestParam(defaultValue = "10") double radiusKm,
            @RequestParam(defaultValue = "distance") String sortBy) {
        return ResponseEntity.ok(ApiResponse.success(mapService.getNearbyAll(lat, lon, radiusKm, sortBy)));
    }

    /**
     * Calculate route distance and estimated time between two coordinates.
     */
    @GetMapping("/route")
    public ResponseEntity<ApiResponse<RouteResponse>> route(
            @RequestParam double originLat,
            @RequestParam double originLon,
            @RequestParam double destLat,
            @RequestParam double destLon) {
        return ResponseEntity.ok(ApiResponse.success(mapService.getRoute(originLat, originLon, destLat, destLon)));
    }
}
