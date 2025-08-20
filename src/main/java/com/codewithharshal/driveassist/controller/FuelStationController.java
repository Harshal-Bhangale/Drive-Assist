package com.codewithharshal.driveassist.controller;

import com.codewithharshal.driveassist.model.FuelStation;
import com.codewithharshal.driveassist.service.FuelStationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fuel-stations")
public class FuelStationController {

    private final FuelStationService fuelStationService;

    public FuelStationController(FuelStationService fuelStationService) {
        this.fuelStationService = fuelStationService;
    }

    @GetMapping
    public List<FuelStation> getAll() {
        return fuelStationService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuelStation> getById(@PathVariable Long id) {
        FuelStation station = fuelStationService.getById(id);
        return station != null ? ResponseEntity.ok(station) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public FuelStation create(@RequestBody FuelStation station) {
        return fuelStationService.save(station);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        fuelStationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nearest")
    public ResponseEntity<FuelStation> findNearest(@RequestParam Double lat, @RequestParam Double lon) {
        FuelStation nearest = fuelStationService.findNearest(lat, lon);
        return nearest != null ? ResponseEntity.ok(nearest) : ResponseEntity.notFound().build();
    }
}