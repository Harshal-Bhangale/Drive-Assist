package com.codewithharshal.driveassist.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

// Inherits Service
@Entity
@Table(name="garages")
public class Garage extends ServiceEntity {

    private String servicesProvided; // Repair, Tire Change

    public Garage(){

    }

    public Garage(String name, double latitude, double longitude, String servicesProvided){
        super(name, latitude, longitude);
        this.servicesProvided = servicesProvided;
    }

    public String getServicesProvided() {
        return servicesProvided;
    }

    public void setServicesProvided(String servicesProvided) {
        this.servicesProvided = servicesProvided;
    }
}
