package com.compassouol.gokuecommerce.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "addresses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private long addressId;

    @NotNull
    @Column(name = "address_zip_code")
    private String addressZipCode;
    
    @NotNull
    @Column(name = "address_street")
    private String addressStreet;
    
    @NotNull
    @Column(name = "address_street_number")
    private String addressStreetNumber;

    @NotNull
    @Column(name = "address_district")
    private String addressDistrict;

    @Column(name = "address_complement")
    private String addressComplement;

    @NotNull
    @Column(name = "address_city")
    private String addressCity;

    @NotNull
    @Column(name = "address_state")
    private String addressState;

    @NotNull
    @Column(name = "address_created_at", updatable = false)
    private LocalDateTime addressCreatedAt = LocalDateTime.now();

    @NotNull
    @Column(name = "address_updated_at")
    private LocalDateTime addressUpdatedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;
}
