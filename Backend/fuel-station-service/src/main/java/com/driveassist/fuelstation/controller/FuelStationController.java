package com.driveassist.fuelstation.controller;

import com.driveassist.common.dto.ApiResponse;
import com.driveassist.common.dto.PagedResponse;
import com.driveassist.fuelstation.dto.FuelStationRequest;
import com.driveassist.fuelstation.dto.FuelStationResponse;
import com.driveassist.fuelstation.enums.FuelType;
import com.driveassist.fuelstation.service.FuelStationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fuel-stations")
public class FuelStationController {

    private final FuelStationService service;

    public FuelStationController(FuelStationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<FuelStationResponse>> create(@Valid @RequestBody FuelStationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Fuel station created", service.create(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<FuelStationResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(service.getById(id)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PagedResponse<FuelStationResponse>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return ResponseEntity.ok(ApiResponse.success(service.getAll(page, size, sortBy)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<FuelStationResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody FuelStationRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Fuel station updated", service.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Fuel station deleted", null));
    }

    @GetMapping("/nearby")
    public ResponseEntity<ApiResponse<List<FuelStationResponse>>> nearby(
            @RequestParam double lat,
            @RequestParam("lon") double lon,
            @RequestParam(defaultValue = "10") double radiusKm,
            @RequestParam(defaultValue = "distance") String sortBy) {
        return ResponseEntity.ok(ApiResponse.success(service.findNearby(lat, lon, radiusKm, sortBy)));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<FuelStationResponse>>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(ApiResponse.success(service.search(keyword)));
    }

    @GetMapping("/type/{fuelType}")
    public ResponseEntity<ApiResponse<List<FuelStationResponse>>> byType(@PathVariable FuelType fuelType) {
        return ResponseEntity.ok(ApiResponse.success(service.findByType(fuelType)));
    }

    @GetMapping("/open-24h")
    public ResponseEntity<ApiResponse<List<FuelStationResponse>>> open24Hours() {
        return ResponseEntity.ok(ApiResponse.success(service.findOpen24Hours()));
    }
}
