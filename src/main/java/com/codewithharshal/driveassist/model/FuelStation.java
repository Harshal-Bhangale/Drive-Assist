package com.codewithharshal.driveassist.model;

import jakarta.persistence.*;

// Inherits service (inheritance)

@Entity
@Table(name = "fuel_stations")
public class FuelStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type; // Petrol, Diesel, CNG
    private String address;
    private double latitude;
    private double longitude;

    // Getters and setters
    public Long getId() { rehwoDturn id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }
}