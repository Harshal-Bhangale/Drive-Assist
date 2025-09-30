package com.app.fuelstation.model;

import jakarta.persistence.*;

@Entity
@Table(name = "fuel_stations", indexes = {
        @Index(name = "idx_fuel_type", columnList = "type"),
        @Index(name = "idx_fuel_lat", columnList = "latitude"),
        @Index(name = "idx_fuel_lon", columnList = "longitude")
})
public class FuelStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String type; // Petrol, Diesel, CNG, EV
    private String address;
    private double latitude;
    private double longitude;
    private String contact;
    private double rating;

    public Long getId() { return id; }
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
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
}



