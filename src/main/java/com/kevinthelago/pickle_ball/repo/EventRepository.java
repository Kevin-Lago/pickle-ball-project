package com.kevinthelago.pickle_ball.repo;

import com.kevinthelago.pickle_ball.dao.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
}
