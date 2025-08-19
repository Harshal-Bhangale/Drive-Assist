package com.codewithharshal.driveassist.controller;

import com.codewithharshal.driveassist.model.ServiceEntity;
import com.codewithharshal.driveassist.service.ServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {
    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    // Get all nearby services
    @GetMapping("/nearby")
    public ResponseEntity<List<ServiceEntity>> getNearby(@RequestParam double lat,
                                                         @RequestParam double lon,
                                                         @RequestParam(defaultValue = "5") double radiusKm) {
        List<ServiceEntity> services = serviceService.getNearbyServices(lat, lon, radiusKm);
        return ResponseEntity.ok(services);
    }
}
