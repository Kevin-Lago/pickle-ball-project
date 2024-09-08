package com.kevinthelago.pickle_ball.dao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Court {
    @Id
    private UUID uuid;
    private int number;
}
