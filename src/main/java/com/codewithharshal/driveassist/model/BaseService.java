package com.codewithharshal.driveassist.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

/**
 * Abstract base for all nearby services (FuelStation, Garage, CarRental).
 * Uses JOINED inheritance for normalized tables per subtype.
 */
@Entity
@Table(name = "services")
@Inheritance(strategy = InheritanceType.JOINED)

/* Include the subtype "type" in JSON so clients know which fields to expect */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = FuelStation.class, name = "FUEL_STATION"),
        @JsonSubTypes.Type(value = Garage.class,      name = "GARAGE"),
        @JsonSubTypes.Type(value = CarRental.class,   name = "CAR_RENTAL")
})
public abstract class BaseService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 120)
    private String name;

    @Column(length = 255)
    private String address;

    @Pattern(regexp = "^[+0-9\\-()\\s]*$", message = "Phone can contain digits and + - ( ) only")
    @Column(length = 40)
    private String phone;

    @Column(length = 160)
    private String website;

    @NotNull
    @DecimalMin("-90.0") @DecimalMax("90.0")
    @Column(nullable = false)
    private Double latitude;

    @NotNull
    @DecimalMin("-180.0") @DecimalMax("180.0")
    @Column(nullable = false)
    private Double longitude;

//    @DecimalMin("0.0") @DecimalMax("5.0")
////    @Column(precision = 2, scale = 1)
//    private Double rating; // optional (0.0 - 5.0)
    @DecimalMin("0.0")
    @DecimalMax("5.0")
    @Column
    private Double rating;
//    @DecimalMin("0.0")
//    @DecimalMax("5.0")
//    @Column(precision = 2, scale = 1)
//    private BigDecimal rating;

    protected BaseService() { }

    protected BaseService(String name, Double latitude, Double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /** Convenience: not persisted. */
    @Transient
    public double distanceKmTo(double userLat, double userLon) {
        final double R = 6371.0; // km
        double dLat = Math.toRadians(userLat - this.latitude);
        double dLon = Math.toRadians(userLon - this.longitude);
        double a = Math.sin(dLat/2)*Math.sin(dLat/2)
                + Math.cos(Math.toRadians(this.latitude))*Math.cos(Math.toRadians(userLat))
                * Math.sin(dLon/2)*Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return R * c;
    }

    // ---------- Getters & Setters ----------

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }
}
