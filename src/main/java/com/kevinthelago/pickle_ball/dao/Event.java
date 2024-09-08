package com.kevinthelago.pickle_ball.dao;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "event")
public class Event {
    @Id
    private UUID uuid;
    private String title;
    private String description;
    @ManyToOne
    private Location location;
    @ManyToOne
    private Court court;
    private Date start;
    private Date end;

    public Event() {
    }

    public Event(UUID uuid, String title, String description, Date start, Date end) {
        this.uuid = uuid;
        this.title = title;
        this.description = description;
        this.start = start;
        this.end = end;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Court getCourt() {
        return court;
    }

    public void setCourt(Court court) {
        this.court = court;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
