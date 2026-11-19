package dev.jhon0206.museums.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "museums")
public class Museum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 150)
    private String name;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String city;

    @Column(length = 200)
    private String address;

    @Column(name = "opening_hours", length = 120)
    private String openingHours;

    @NotNull
    @DecimalMin("0.00")
    @Column(name = "admission_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal admissionPrice;

    public Museum() {
    }

    public Museum(Long id, String name, String city, String address, String openingHours, BigDecimal admissionPrice) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
        this.openingHours = openingHours;
        this.admissionPrice = admissionPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public BigDecimal getAdmissionPrice() {
        return admissionPrice;
    }

    public void setAdmissionPrice(BigDecimal admissionPrice) {
        this.admissionPrice = admissionPrice;
    }
}
