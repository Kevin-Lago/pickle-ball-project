package com.kevinthelago.pickle_ball.constants.iso_codes;

public enum CountryCodes {
    BRAZIL("Brazil", "Brasil", BrazilStateCodes.values());

    private String englishName;
    private String localName;
    private IStateCodes[] stateCodes;

    CountryCodes(String englishName, String localName, IStateCodes[] stateCodes) {
        this.englishName = englishName;
        this.localName = localName;
        this.stateCodes = stateCodes;
    }
}
