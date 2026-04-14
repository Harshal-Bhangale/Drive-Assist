package com.driveassist.map.client;

import com.driveassist.common.dto.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Feign client targeting the fuel-station-service via Eureka.
 */
@FeignClient(name = "fuel-station-service", fallbackFactory = FuelStationClientFallback.class)
public interface FuelStationClient {

    @GetMapping("/api/v1/fuel-stations/nearby")
    ApiResponse<List<Map<String, Object>>> getNearby(
            @RequestParam("lat") double lat,
            @RequestParam("lon") double lon,
            @RequestParam("radiusKm") double radiusKm,
            @RequestParam("sortBy") String sortBy
    );
}
