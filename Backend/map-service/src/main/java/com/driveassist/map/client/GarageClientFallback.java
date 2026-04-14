package com.driveassist.map.client;

import com.driveassist.common.dto.ApiResponse;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class GarageClientFallback implements FallbackFactory<GarageClient> {

    @Override
    public GarageClient create(Throwable cause) {
        return (lat, lon, radiusKm, sortBy) ->
                ApiResponse.error("Garage service unavailable: " + cause.getMessage(), Collections.emptyList());
    }
}
