package com.codewithharshal.driveassist.model;
// Base class for all service

import jakarta.persistence.*;

@Entity
@Table(name = "services")
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;       // Name of the service (e.g., "HP Petrol Pump")
    private String type;       // Type (fuel, garage, rental, hotel)
    private double latitude;   // Latitude of service location
    private double longitude;  // Longitude of service location
    private String address;    // Optional: service address
    private String contact;    // Optional: phone/email

    public ServiceEntity() {}

    public ServiceEntity(String name, String type, double latitude, double longitude, String address, String contact) {
        this.name = name;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.contact = contact;
    }

    // Getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
}