package com.driveassist.garage.controller;

import com.driveassist.common.dto.ApiResponse;
import com.driveassist.common.dto.PagedResponse;
import com.driveassist.garage.dto.GarageRequest;
import com.driveassist.garage.dto.GarageResponse;
import com.driveassist.garage.enums.GarageSpecialization;
import com.driveassist.garage.service.GarageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/garages")
public class GarageController {

    private final GarageService service;

    public GarageController(GarageService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<GarageResponse>> create(@Valid @RequestBody GarageRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Garage created", service.create(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GarageResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(service.getById(id)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PagedResponse<GarageResponse>>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return ResponseEntity.ok(ApiResponse.success(service.getAll(page, size, sortBy)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<GarageResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody GarageRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Garage updated", service.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Garage deleted", null));
    }

    @GetMapping("/nearby")
    public ResponseEntity<ApiResponse<List<GarageResponse>>> nearby(
            @RequestParam double lat,
            @RequestParam("lon") double lon,
            @RequestParam(defaultValue = "10") double radiusKm,
            @RequestParam(defaultValue = "distance") String sortBy) {
        return ResponseEntity.ok(ApiResponse.success(service.findNearby(lat, lon, radiusKm, sortBy)));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<GarageResponse>>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(ApiResponse.success(service.search(keyword)));
    }

    @GetMapping("/specialization/{specialization}")
    public ResponseEntity<ApiResponse<List<GarageResponse>>> bySpecialization(
            @PathVariable GarageSpecialization specialization) {
        return ResponseEntity.ok(ApiResponse.success(service.findBySpecialization(specialization)));
    }

    @GetMapping("/emergency")
    public ResponseEntity<ApiResponse<List<GarageResponse>>> emergencyGarages() {
        return ResponseEntity.ok(ApiResponse.success(service.findEmergencyGarages()));
    }
}
