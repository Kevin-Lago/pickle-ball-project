package com.kevinthelago.pickle_ball.constants.iso_codes;

public enum BrazilStateCodes implements IStateCodes {
    BR_AC("BR-AC", "Acre", Type.STATE);

    private String code;
    private String name;
    private Type category;

    BrazilStateCodes(String code, String name, Type category) {
        this.code = code;
        this.name = name;
        this.category = category;
    }

    enum Type {
        STATE,
        FEDERAL_DISTRICT
    }
}
