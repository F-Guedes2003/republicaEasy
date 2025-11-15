package org.republica.easy.republicaeasy.Entities;

import jakarta.persistence.*;

import java.util.UUID;

@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String password;
    private String email;
    @Embedded
    private Localization localization;

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Localization getLocalization() {
        return localization;
    }

    public void setLocalization(Localization address) {
        this.localization = address;
    }
}
