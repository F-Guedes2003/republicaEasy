package org.republica.easy.republicaeasy.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.util.UUID;

@Entity
public class User {
    @Id
    private UUID id;
    private String name;
    private String password;
    private String email;
    @OneToOne
    private Address address;

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
