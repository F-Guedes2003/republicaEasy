package org.republica.easy.republicaeasy.Entities;

import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Localization {

    private String city;
    private String state;
    private String neighborhood;
    private Integer number;
    private String cep;
    private String street;

    public Localization() {}

    public Localization(String city, String state, String neighborhood, int number, String cep, String street) {
        this.city = city;
        this.state = state;
        this.neighborhood = neighborhood;
        this.number = number;
        this.cep = cep;
        this.street = street;
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

    public String getNeighborhood() {return neighborhood;}

    public void setNeighborhood(String neighborhood) {this.neighborhood = neighborhood;}

    public Integer getNumber() {return number;}

    public void setNumber(int number) {this.number = number;}

    public String getCep() {return cep;}

    public void setCep(String cep) {this.cep = cep;}

    public String getStreet() {return street;}

    public void setStreet(String street) {this.street = street;}

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
        return "Localization{" +
                "city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", number=" + number +
                ", cep='" + cep + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
