package com.app.rental.controller;

import com.app.rental.dto.BookingConfirmation;
import com.app.rental.dto.BookingRequest;
import com.app.rental.dto.CarRentalDTO;
import com.app.rental.service.CarRentalService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class CarRentalController {
    private final CarRentalService service;

    public CarRentalController(CarRentalService service) { this.service = service; }

    @GetMapping("/available")
    public List<CarRentalDTO> available(@RequestParam double lat,
                                        @RequestParam("long") double lon,
                                        @RequestParam(required = false) String type,
                                        @RequestParam(required = false) Double maxPrice,
                                        @RequestParam(required = false, defaultValue = "distance") String sortBy) {
        return service.getAvailable(lat, lon, type, maxPrice, sortBy);
    }

    @PostMapping("/book")
    public ResponseEntity<BookingConfirmation> book(@Valid @RequestBody BookingRequest request) {
        return ResponseEntity.ok(service.book(request));
    }
}



