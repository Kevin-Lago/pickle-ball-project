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

    public Event create(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> create(List<Event> events) {
        return eventRepository.saveAll(events);
    }

    public List<Event> get() {
        return eventRepository.findAll();
    }

    public Event get(UUID uuid) {
        Optional<Event> eventOptional = eventRepository.findById(uuid);

        if (eventOptional.isEmpty()) throw new EventNotFoundException("Event not found with uuid: " + uuid);

        return eventOptional.get();
    }

    public Event update(Event event) {
        Optional<Event> eventOptional = eventRepository.findById(event.getUuid());

        if (eventOptional.isEmpty()) throw new EventNotFoundException("Event not found with uuid: " + event.getUuid());

        return eventOptional.get();
    }

    public List<Event> update(List<Event> events) {
        return events.stream().map(event -> {
            try {
                return update(event);
            } catch (EventNotFoundException e) {
                return null;
            }
        }).collect(Collectors.toList());
    }

    public void delete(UUID uuid) {
        Optional<Event> eventOptional = eventRepository.findById(uuid);

        if (eventOptional.isEmpty()) throw new EventNotFoundException("Event not found with uuid: " + uuid);

        eventRepository.deleteById(uuid);
    }

    public void delete(List<UUID> uuids) {
        eventRepository.deleteAllById(uuids);
    }
}
