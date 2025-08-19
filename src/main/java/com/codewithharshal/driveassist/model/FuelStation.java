package com.codewithharshal.driveassist.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

// Inherits service (inheritance)

@Entity
@Table(name = "fuel_stations")
public class FuelStation extends Service {

    private String fuelType; // Petrol, Disel, CNG

    public FuelStation(){

    }

    public FuelStation(String name, double latitude, double longitude, String fuelType) {
        super(name, latitude, longitude);
        this.fuelType = fuelType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}
