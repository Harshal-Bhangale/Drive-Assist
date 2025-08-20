package com.codewithharshal.driveassist.controller;

import com.codewithharshal.driveassist.model.CarRental;
import com.codewithharshal.driveassist.service.CarRentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car-rentals")
public class CarRentalController {

    private final CarRentalService carRentalService;

    public CarRentalController(CarRentalService carRentalService) {
        this.carRentalService = carRentalService;
    }

    @GetMapping
    public List<CarRental> getAll() {
        return carRentalService.getAll();
    }

    @GetMapping("/hello")
    public String hello(String name)
    {
        return name;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarRental> getById(@PathVariable Long id) {
        CarRental car = carRentalService.getById(id);
        return car != null ? ResponseEntity.ok(car) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public CarRental create(@RequestBody CarRental car) {
        return carRentalService.save(car);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        carRentalService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/nearest")
    public CarRental getNearestAvailableCar(@RequestParam String carType,
                                            @RequestParam Double latitude,
                                            @RequestParam Double longitude) {
        return carRentalService.findAvailableCar(carType, latitude, longitude);
    }

}
