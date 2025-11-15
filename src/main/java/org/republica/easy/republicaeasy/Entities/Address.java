package org.republica.easy.republicaeasy.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Address {
    @Id
    private UUID id;
    private String city;
    private String state;
    private String country;
}
