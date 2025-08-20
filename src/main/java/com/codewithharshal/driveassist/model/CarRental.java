package com.codewithharshal.driveassist.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "car_rentals")
public class CarRental extends BaseService {

    @Min(0)
    @Column(nullable = false)
    private int availableCars = 0;

    /** Optional: avg price per day */
    @Min(0)
    private Double pricePerDay;

    /** Optional: whether drivers are available */
    @Column(nullable = false)
    private boolean driverAvailable = false;

    @NotBlank
    @Column(nullable = false, length = 32)
    private String carType;

    public CarRental() { }

    public CarRental(String name, Double lat, Double lon, int availableCars, String carType) {
        super(name, lat, lon);
        this.availableCars = availableCars;
        this.carType = carType;
    }

    // Getters & Setters
    public int getAvailableCars() { return availableCars; }
    public void setAvailableCars(int availableCars) { this.availableCars = availableCars; }

    public Double getPricePerDay() { return pricePerDay; }
    public void setPricePerDay(Double pricePerDay) { this.pricePerDay = pricePerDay; }

    public boolean isDriverAvailable() { return driverAvailable; }
    public void setDriverAvailable(boolean driverAvailable) { this.driverAvailable = driverAvailable; }

    public String getCarType() { return carType; }
    public void setCarType(String carType) { this.carType = carType; }

    /** Convenience method to check if any cars are available */
    @Transient
    public boolean isAvailable() {
        return availableCars > 0;
    }
}
