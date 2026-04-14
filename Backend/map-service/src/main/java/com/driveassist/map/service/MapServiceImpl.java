package com.driveassist.map.service;

import com.driveassist.common.dto.ApiResponse;
import com.driveassist.common.util.GeoUtils;
import com.driveassist.map.client.FuelStationClient;
import com.driveassist.map.client.GarageClient;
import com.driveassist.map.client.RentalClient;
import com.driveassist.map.dto.MapResponse;
import com.driveassist.map.dto.MarkerResponse;
import com.driveassist.map.dto.RouteResponse;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Aggregation service — composes responses from three downstream microservices
 * using Feign clients with Resilience4j circuit-breaker fall-backs.
 */
@Service
public class MapServiceImpl implements MapService {

    private final FuelStationClient fuelStationClient;
    private final GarageClient garageClient;
    private final RentalClient rentalClient;

    public MapServiceImpl(FuelStationClient fuelStationClient,
                          GarageClient garageClient,
                          RentalClient rentalClient) {
        this.fuelStationClient = fuelStationClient;
        this.garageClient = garageClient;
        this.rentalClient = rentalClient;
    }

    @Override
    public MapResponse getNearbyAll(double lat, double lon, double radiusKm, String sortBy) {
        List<MarkerResponse> fuelMarkers = toMarkers("FUEL_STATION", fuelStationClient.getNearby(lat, lon, radiusKm, sortBy));
        List<MarkerResponse> garageMarkers = toMarkers("GARAGE", garageClient.getNearby(lat, lon, radiusKm, sortBy));
        List<MarkerResponse> rentalMarkers = toMarkers("RENTAL_CAR", rentalClient.getNearby(lat, lon, radiusKm, sortBy));

        MapResponse response = new MapResponse();
        response.setCenterLat(lat);
        response.setCenterLon(lon);
        response.setRadiusKm(radiusKm);
        response.setFuelStations(fuelMarkers);
        response.setGarages(garageMarkers);
        response.setRentalCars(rentalMarkers);
        response.setTotalMarkers(fuelMarkers.size() + garageMarkers.size() + rentalMarkers.size());
        return response;
    }

    @Override
    public RouteResponse getRoute(double originLat, double originLon, double destLat, double destLon) {
        double distanceKm = GeoUtils.calculateDistanceKm(originLat, originLon, destLat, destLon);
        // Assume average speed of 40 km/h in city driving
        int estimatedMinutes = (int) Math.ceil((distanceKm / 40.0) * 60);
        String summary = String.format("%.1f km, approximately %d min via main roads", distanceKm, estimatedMinutes);

        return new RouteResponse(originLat, originLon, destLat, destLon, distanceKm, estimatedMinutes, summary);
    }

    // ---- Helpers ----

    private List<MarkerResponse> toMarkers(String type, ApiResponse<List<Map<String, Object>>> apiResponse) {
        if (apiResponse == null || apiResponse.getData() == null) {
            return Collections.emptyList();
        }
        return apiResponse.getData().stream()
                .map(raw -> {
                    MarkerResponse marker = new MarkerResponse();
                    marker.setType(type);
                    marker.setName(stringVal(raw, "name", "model"));
                    marker.setLat(doubleVal(raw, "latitude", "pickupLat"));
                    marker.setLon(doubleVal(raw, "longitude", "pickupLon"));
                    marker.setAddress(stringVal(raw, "address", "pickupAddress"));
                    marker.setDistanceKm(doubleVal(raw, "distanceKm"));
                    marker.setRating(doubleVal(raw, "rating"));
                    marker.setDetails(raw);
                    return marker;
                })
                .toList();
    }

    private String stringVal(Map<String, Object> raw, String... keys) {
        for (String key : keys) {
            Object value = raw.get(key);
            if (value != null) return value.toString();
        }
        return "";
    }

    private double doubleVal(Map<String, Object> raw, String... keys) {
        for (String key : keys) {
            Object value = raw.get(key);
            if (value instanceof Number n) return n.doubleValue();
        }
        return 0;
    }
}
