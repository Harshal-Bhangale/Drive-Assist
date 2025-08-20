package com.codewithharshal.driveassist.model;

import com.codewithharshal.driveassist.model.enums.FuelType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "fuel_stations")
public class FuelStation extends BaseService {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 16)
    private FuelType fuelType;

    @NotNull
    @Column(nullable = false)
    private Boolean open24x7 = Boolean.FALSE;

    public FuelStation() { }

    public FuelStation(String name, Double lat, Double lon, FuelType fuelType, boolean open24x7) {
        super(name, lat, lon);
        this.fuelType = fuelType;
        this.open24x7 = open24x7;
    }

    public FuelType getFuelType() { return fuelType; }
    public void setFuelType(FuelType fuelType) { this.fuelType = fuelType; }

    public Boolean getOpen24x7() { return open24x7; }
    public void setOpen24x7(Boolean open24x7) { this.open24x7 = open24x7; }
}
