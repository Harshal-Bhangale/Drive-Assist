package com.driveassist.fuelstation.dto;

import com.driveassist.fuelstation.enums.FuelType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FuelStationRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Fuel type is required")
    private FuelType fuelType;

    private String address;
    private String city;
    private String state;
    private double latitude;
    private double longitude;
    private String contact;
    private double rating;
    private boolean open24Hours;

    // --- Getters & Setters ---

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public FuelType getFuelType() { return fuelType; }

    public void setFuelType(FuelType fuelType) { this.fuelType = fuelType; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }

    public void setState(String state) { this.state = state; }

    public double getLatitude() { return latitude; }

    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }

    public void setLongitude(double longitude) { this.longitude = longitude; }

    public String getContact() { return contact; }

    public void setContact(String contact) { this.contact = contact; }

    public double getRating() { return rating; }

    public void setRating(double rating) { this.rating = rating; }

    public boolean isOpen24Hours() { return open24Hours; }

    public void setOpen24Hours(boolean open24Hours) { this.open24Hours = open24Hours; }
}
