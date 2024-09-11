package com.kevinthelago.pickle_ball.dao;

import com.kevinthelago.pickle_ball.constants.iso_codes.CountryCodes;
import com.kevinthelago.pickle_ball.constants.iso_codes.IStateCodes;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private String street1;
    private String street2;
    private String street3;
    private String city;
    @ElementCollection
    private IStateCodes state;
    private CountryCodes country;
    private String zipCode;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getStreet3() {
        return street3;
    }

    public void setStreet3(String street3) {
        this.street3 = street3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public IStateCodes getState() {
        return state;
    }

    public void setState(IStateCodes state) {
        this.state = state;
    }

    public CountryCodes getCountry() {
        return country;
    }

    public void setCountry(CountryCodes country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
