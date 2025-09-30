package com.app.map.controller;

import com.app.map.dto.MapDTO;
import com.app.map.dto.RouteDTO;
import com.app.map.service.MapService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/map")
public class MapController {
    private final MapService mapService;

    public MapController(MapService mapService) { this.mapService = mapService; }

    @GetMapping("/nearby")
    public MapDTO nearby(@RequestParam double lat, @RequestParam("long") double lon,
                         @RequestParam(defaultValue = "10") double radiusKm) {
        return mapService.nearby(lat, lon, radiusKm);
    }

    @GetMapping("/route")
    public RouteDTO route(@RequestParam double userLat, @RequestParam("userLong") double userLon,
                          @RequestParam double destLat, @RequestParam("destLong") double destLon) {
        return mapService.route(userLat, userLon, destLat, destLon);
    }
}



