package com.app.garage.controller;

import com.app.garage.dto.GarageDTO;
import com.app.garage.service.GarageService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/garages")
public class GarageController {
    private final GarageService service;

    public GarageController(GarageService service) { this.service = service; }

    @GetMapping("/nearby")
    public List<GarageDTO> nearby(@RequestParam double lat,
                                  @RequestParam("long") double lon,
                                  @RequestParam(required = false) String serviceType,
                                  @RequestParam(required = false, defaultValue = "distance") String sortBy) {
        return service.getNearby(lat, lon, serviceType, sortBy);
    }
}



