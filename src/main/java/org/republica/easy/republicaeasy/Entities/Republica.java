package org.republica.easy.republicaeasy.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Republica {

    @Id
    private UUID id;
    private String name;
    private String description;
    private String localization;
    private String imageUrl;
    private int limit;
    private int contact;

    protected Republica() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
