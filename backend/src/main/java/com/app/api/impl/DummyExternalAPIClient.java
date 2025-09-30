package com.app.api.impl;

import com.app.api.ExternalAPIClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DummyExternalAPIClient implements ExternalAPIClient {
    @Override
    public DirectionsDTO getDirections(double startLat, double startLon, double endLat, double endLon) {
        double dx = endLat - startLat;
        double dy = endLon - startLon;
        double approxKm = Math.sqrt(dx * dx + dy * dy) * 111.0; // rough
        double minutes = approxKm / 40.0 * 60.0; // assume 40km/h
        return new DirectionsDTO(approxKm, minutes, "", List.of("Head to destination"));
    }
}



