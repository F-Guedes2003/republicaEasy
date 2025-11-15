package org.republica.easy.republicaeasy.Entities;

import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Localization {

    private String city;
    private String state;

    public Localization() {}

    public Localization(String city, String state) {
        this.city = city;
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Localization loc = (Localization) obj;
        return Objects.equals(city, loc.city) &&
                Objects.equals(state, loc.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, state);
    }

    @Override
    public String toString() {
        return "Localization{city='%s', state='%s'}".formatted(city, state);
    }
}
