package com.driveassist.garage.entity;

import com.driveassist.common.entity.BaseEntity;
import com.driveassist.garage.enums.GarageSpecialization;
import jakarta.persistence.*;

/**
 * Garage entity — inherits from BaseEntity (OOP Inheritance).
 */
@Entity
@Table(name = "garages", indexes = {
        @Index(name = "idx_garage_lat", columnList = "latitude"),
        @Index(name = "idx_garage_lon", columnList = "longitude"),
        @Index(name = "idx_garage_specialization", columnList = "specialization")
})
public class Garage extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GarageSpecialization specialization;

    @Column(length = 2000)
    private String servicesOffered;

    private String address;
    private String city;
    private String state;
    private double latitude;
    private double longitude;
    private String contact;
    private double rating;
    private boolean emergencyService;

    // --- Getters & Setters ---

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public GarageSpecialization getSpecialization() { return specialization; }

    public void setSpecialization(GarageSpecialization specialization) { this.specialization = specialization; }

    public String getServicesOffered() { return servicesOffered; }

    public void setServicesOffered(String servicesOffered) { this.servicesOffered = servicesOffered; }

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

    public boolean isEmergencyService() { return emergencyService; }

    public void setEmergencyService(boolean emergencyService) { this.emergencyService = emergencyService; }
}
