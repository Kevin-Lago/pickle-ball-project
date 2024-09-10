package com.kevinthelago.pickle_ball.dao;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Court {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private int number;
    @ManyToOne
    private Location location;

    public Court() {
    }

    public Court(UUID uuid, int number, Location location) {
        this.uuid = uuid;
        this.number = number;
        this.location = location;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
