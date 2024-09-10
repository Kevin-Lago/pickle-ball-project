package com.kevinthelago.pickle_ball.util;

import com.kevinthelago.pickle_ball.dao.Address;
import com.kevinthelago.pickle_ball.dao.Court;
import com.kevinthelago.pickle_ball.dao.Event;
import com.kevinthelago.pickle_ball.dao.Location;

import java.util.Date;
import java.util.UUID;

public class DocumentGenerator {
    public static Court createTestCourt() {
        return new Court(
                UUID.randomUUID(),
                1,
                new Location()
        );
    }

    public static Event createTestEvent() {
        return new Event(
                UUID.randomUUID(),
                "",
                "",
                new Date(),
                new Date()
        );
    }
    private String street1;
    private String street2;
    private String street3;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    public static Location createTestLocation() {
        return new Location(
                UUID.randomUUID(),
                new Address()
        );
    }
}
