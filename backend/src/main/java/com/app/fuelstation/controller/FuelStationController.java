package com.app.fuelstation.controller;

import com.app.fuelstation.dto.FuelStationDTO;
import com.app.fuelstation.service.FuelStationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fuel-stations")
public class FuelStationController {
    private final FuelStationService service;

    public FuelStationController(FuelStationService service) { this.service = service; }

    @GetMapping("/nearby")
    public List<FuelStationDTO> nearby(@RequestParam double lat,
                                       @RequestParam("long") double lon,
                                       @RequestParam(required = false) String type,
                                       @RequestParam(required = false, defaultValue = "distance") String sortBy) {
        return service.getNearby(lat, lon, type, sortBy);
    }
}



