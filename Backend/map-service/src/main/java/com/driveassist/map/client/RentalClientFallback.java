package com.driveassist.map.client;

import com.driveassist.common.dto.ApiResponse;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class RentalClientFallback implements FallbackFactory<RentalClient> {

    @Override
    public RentalClient create(Throwable cause) {
        return (lat, lon, radiusKm, sortBy) ->
                ApiResponse.error("Rental service unavailable: " + cause.getMessage(), Collections.emptyList());
    }
}
