package com.codewithharshal.driveassist.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "car_rentals")
public class CarRental extends BaseService {

    @Min(0)
    @Column(nullable = false)
    private int availableCars = 0;

    /** Optional: avg price per day in local currency */
    @Min(0)
    private Double pricePerDay;

    /** Optional: whether drivers are available with rentals */
    @Column(nullable = false)
    private boolean driverAvailable = false;

    @Column(nullable = false, length = 32)
    private String carType; // NEW FIELD

    public CarRental() { }

    public CarRental(String name, Double lat, Double lon, int availableCars, String carType) {
        super(name, lat, lon);
        this.availableCars = availableCars;
        this.carType = carType;
    }

    public int getAvailableCars() { return availableCars; }
    public void setAvailableCars(int availableCars) { this.availableCars = availableCars; }

    public Double getPricePerDay() { return pricePerDay; }
    public void setPricePerDay(Double pricePerDay) { this.pricePerDay = pricePerDay; }

    public boolean isDriverAvailable() { return driverAvailable; }
    public void setDriverAvailable(boolean driverAvailable) { this.driverAvailable = driverAvailable; }

    public String getCarType() { return carType; } // NEW GETTER
    public void setCarType(String carType) { this.carType = carType; }

    public boolean isAvailable() {
        return availableCars > 0;
    }


}
