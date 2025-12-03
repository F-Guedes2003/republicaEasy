package org.republica.easy.republicaeasy.Entities;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class Republica {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    @Embedded
    private Localization localization;
    private String imageUrl;
    private int limitSpot;
    private String contact;
    @OneToMany(mappedBy = "republica", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();

    protected Republica() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Localization getLocalization() {
        return localization;
    }

    public void setLocalization(Localization localization) {
        this.localization = localization;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getLimitSpot() {
        return limitSpot;
    }

    public void setLimitSpot(int limitSpot) {
        this.limitSpot = limitSpot;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public List<User> getUsers() { return users; }

    public void addUser(User user) {
        users.add(user);
        user.setRepublica(this);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.setRepublica(null);
    }

    @Override
    public String toString() {
        return "Republica{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", localization=" + localization +
                ", imageUrl='" + imageUrl + '\'' +
                ", limitSpot=" + limitSpot +
                ", contact='" + contact + '\'' +
                ", users=" + users +
                '}';
    }
}
