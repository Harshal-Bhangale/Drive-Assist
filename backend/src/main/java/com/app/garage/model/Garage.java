package com.app.garage.model;

import jakarta.persistence.*;

@Entity
@Table(name = "garages", indexes = {
        @Index(name = "idx_garage_lat", columnList = "latitude"),
        @Index(name = "idx_garage_lon", columnList = "longitude")
})
public class Garage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(length = 2000)
    private String servicesOffered;
    private String address;
    private double latitude;
    private double longitude;
    private String contact;
    private double rating;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getServicesOffered() { return servicesOffered; }
    public void setServicesOffered(String servicesOffered) { this.servicesOffered = servicesOffered; }
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



