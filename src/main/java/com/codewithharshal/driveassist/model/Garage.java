package com.codewithharshal.driveassist.model;

import com.codewithharshal.driveassist.model.enums.GarageServiceType;
import jakarta.persistence.*;

import java.util.EnumSet;

@Entity
@Table(name = "garages")
public class Garage extends BaseService {

    /** Services offered by this garage (stored in join table) */
    @ElementCollection(targetClass = GarageServiceType.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "garage_services", joinColumns = @JoinColumn(name = "garage_id"))
    @Column(name = "service", length = 32, nullable = false)
    private EnumSet<GarageServiceType> servicesProvided = EnumSet.noneOf(GarageServiceType.class);

    @Column(nullable = false)
    private boolean emergencySupport = false;

    public Garage() { }

    public Garage(String name, Double lat, Double lon, EnumSet<GarageServiceType> servicesProvided) {
        super(name, lat, lon);
        this.servicesProvided = servicesProvided == null ? EnumSet.noneOf(GarageServiceType.class) : servicesProvided;
    }

    public EnumSet<GarageServiceType> getServicesProvided() { return servicesProvided; }
    public void setServicesProvided(EnumSet<GarageServiceType> servicesProvided) { this.servicesProvided = servicesProvided; }

    public boolean isEmergencySupport() { return emergencySupport; }
    public void setEmergencySupport(boolean emergencySupport) { this.emergencySupport = emergencySupport; }
}
