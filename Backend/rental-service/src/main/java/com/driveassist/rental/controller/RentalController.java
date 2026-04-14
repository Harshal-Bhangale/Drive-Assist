package com.driveassist.rental.controller;

import com.driveassist.common.dto.ApiResponse;
import com.driveassist.common.dto.PagedResponse;
import com.driveassist.rental.dto.*;
import com.driveassist.rental.enums.CarType;
import com.driveassist.rental.service.RentalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rentals")
public class RentalController {

    private final RentalService service;

    public RentalController(RentalService service) {
        this.service = service;
    }

    // ========== Car Endpoints ==========

    @PostMapping("/cars")
    public ResponseEntity<ApiResponse<CarRentalResponse>> createCar(@Valid @RequestBody CarRentalRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Car added", service.create(request)));
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<ApiResponse<CarRentalResponse>> getCarById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(service.getById(id)));
    }

    @GetMapping("/cars")
    public ResponseEntity<ApiResponse<PagedResponse<CarRentalResponse>>> getAllCars(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return ResponseEntity.ok(ApiResponse.success(service.getAll(page, size, sortBy)));
    }

    @PutMapping("/cars/{id}")
    public ResponseEntity<ApiResponse<CarRentalResponse>> updateCar(
            @PathVariable Long id,
            @Valid @RequestBody CarRentalRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Car updated", service.update(id, request)));
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Car deleted", null));
    }

    @GetMapping("/cars/available")
    public ResponseEntity<ApiResponse<List<CarRentalResponse>>> available(
            @RequestParam double lat,
            @RequestParam("lon") double lon,
            @RequestParam(required = false) CarType type,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(defaultValue = "distance") String sortBy) {
        return ResponseEntity.ok(ApiResponse.success(service.findAvailable(lat, lon, type, maxPrice, sortBy)));
    }

    @GetMapping("/cars/nearby")
    public ResponseEntity<ApiResponse<List<CarRentalResponse>>> nearby(
            @RequestParam double lat,
            @RequestParam("lon") double lon,
            @RequestParam(defaultValue = "10") double radiusKm,
            @RequestParam(defaultValue = "distance") String sortBy) {
        return ResponseEntity.ok(ApiResponse.success(service.findNearby(lat, lon, radiusKm, sortBy)));
    }

    @GetMapping("/cars/search")
    public ResponseEntity<ApiResponse<List<CarRentalResponse>>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(ApiResponse.success(service.search(keyword)));
    }

    // ========== Booking Endpoints ==========

    @PostMapping("/bookings")
    public ResponseEntity<ApiResponse<BookingResponse>> book(@Valid @RequestBody BookingRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Booking confirmed", service.createBooking(request)));
    }

    @GetMapping("/bookings/{id}")
    public ResponseEntity<ApiResponse<BookingResponse>> getBooking(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(service.getBooking(id)));
    }

    @PutMapping("/bookings/{id}/cancel")
    public ResponseEntity<ApiResponse<BookingResponse>> cancelBooking(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Booking cancelled", service.cancelBooking(id)));
    }

    @GetMapping("/bookings/user/{userId}")
    public ResponseEntity<ApiResponse<List<BookingResponse>>> userBookings(@PathVariable Long userId) {
        return ResponseEntity.ok(ApiResponse.success(service.getUserBookings(userId)));
    }

    @GetMapping("/bookings/user/{userId}/paged")
    public ResponseEntity<ApiResponse<PagedResponse<BookingResponse>>> userBookingsPaged(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(ApiResponse.success(service.getUserBookingsPaged(userId, page, size)));
    }
}
