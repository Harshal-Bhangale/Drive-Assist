package com.driveassist.map.client;

import com.driveassist.common.dto.ApiResponse;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class FuelStationClientFallback implements FallbackFactory<FuelStationClient> {

    @Override
    public FuelStationClient create(Throwable cause) {
        return (lat, lon, radiusKm, sortBy) ->
                ApiResponse.error("Fuel-station service unavailable: " + cause.getMessage(), Collections.emptyList());
    }
}
