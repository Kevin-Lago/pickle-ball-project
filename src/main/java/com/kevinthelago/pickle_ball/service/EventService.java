package com.kevinthelago.pickle_ball.service;

import com.kevinthelago.pickle_ball.dao.Event;
import com.kevinthelago.pickle_ball.exception.EventNotFoundException;
import com.kevinthelago.pickle_ball.repo.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> createEvents(List<Event> events) {
        return events.stream().map(this::createEvent).collect(Collectors.toList());
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEvent(UUID uuid) {
        Optional<Event> eventOptional = eventRepository.findById(uuid);

        if (eventOptional.isEmpty()) throw new EventNotFoundException("Event not found with uuid: " + uuid);

        return eventOptional.get();
    }
}
