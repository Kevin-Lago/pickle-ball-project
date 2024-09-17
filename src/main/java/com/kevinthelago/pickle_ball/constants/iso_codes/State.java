package com.kevinthelago.pickle_ball.constants.iso_codes;

import java.io.Serializable;

public enum State implements Serializable {
    BR_AC("BR-AC", "Acre", Country.BRAZIL, Type.STATE);

    private String code;
    private String name;
    private Country country;
    private Type category;

    State(String code, String name, Country country, Type category) {
        this.code = code;
        this.name = name;
        this.country = country;
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    public Type getCategory() {
        return category;
    }

    enum Type {
        STATE,
        FEDERAL_DISTRICT
    }
}
