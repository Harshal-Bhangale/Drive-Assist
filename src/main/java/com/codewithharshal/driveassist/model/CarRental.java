package com.codewithharshal.driveassist.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

//Inherits Service
@Entity
@Table(name = "car_rentals")
public class CarRental extends Service {

    private int availableCars;

    public CarRental() {}

    public CarRental(String name, double latitude, double longitude, int availableCars) {
        super(name, latitude, longitude);
        this.availableCars = availableCars;
    }

    public int getAvailableCars() { return availableCars; }
    public void setAvailableCars(int availableCars) { this.availableCars = availableCars; }
}