package com.kevinthelago.pickle_ball.constants.iso_codes;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Country implements Serializable {
    BRAZIL("Brazil", "Brasil", "BR", State.values());

    private String englishName;
    private String actualName;
    private String iso3166_1Alpha2CountryCode;
    private State[] states;

    Country(String englishName, String actualName, String iso3166_1Alpha2CountryCode, State[] states) {
        this.englishName = englishName;
        this.actualName = actualName;
        this.iso3166_1Alpha2CountryCode = iso3166_1Alpha2CountryCode;
        this.states = states;
    }

    public String getEnglishName() {
        return englishName;
    }

    public String getActualName() {
        return actualName;
    }

    public String getIso3166_1Alpha2CountryCode() {
        return iso3166_1Alpha2CountryCode;
    }

    public State[] getStates() {
        return states;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();

        map.put("englishName", this.englishName);
        map.put("actualName", this.actualName);
        map.put("iso3166_1Alpha2CountryCode", this.iso3166_1Alpha2CountryCode);
        map.put("states", this.states);

        return map;
    }

    @Override
    public String toString() {
        return "{" +
                "englishName='" + englishName + '\'' +
                ", actualName='" + actualName + '\'' +
                ", iso3166_1Alpha2CountryCode='" + iso3166_1Alpha2CountryCode + '\'' +
                ", states=" + Arrays.toString(states) +
                '}';
    }
}
