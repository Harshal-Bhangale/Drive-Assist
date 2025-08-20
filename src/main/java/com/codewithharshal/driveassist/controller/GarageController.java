package com.codewithharshal.driveassist.controller;

import com.codewithharshal.driveassist.model.Garage;
import com.codewithharshal.driveassist.service.GarageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/garages")
public class GarageController {
    private final GarageService garageService;

    public GarageController(GarageService garageService) {
        this.garageService = garageService;
    }

    @GetMapping
    public List<Garage> getAll() {
        return garageService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Garage> getById(@PathVariable Long id) {
        Garage garage = garageService.getById(id);
        return garage != null ? ResponseEntity.ok(garage) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Garage create(@RequestBody Garage garage) {
        return garageService.save(garage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        garageService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nearest")
    public ResponseEntity<Garage> findNearest(@RequestParam Double lat, @RequestParam Double lon) {
        Garage nearest = garageService.findNearest(lat, lon);
        return nearest != null ? ResponseEntity.ok(nearest) : ResponseEntity.notFound().build();
    }
}
