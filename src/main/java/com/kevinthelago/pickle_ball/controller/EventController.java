package com.kevinthelago.pickle_ball.controller;

import com.kevinthelago.pickle_ball.dao.Event;
import com.kevinthelago.pickle_ball.exception.EventNotFoundException;
import com.kevinthelago.pickle_ball.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.create(event));
    }

    @PostMapping("/")
    public ResponseEntity<List<Event>> createEvents(@RequestBody List<Event> events) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.create(events));
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok().body(eventService.get());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Event> getEvent(@PathVariable("uuid") UUID uuid) {
        return ResponseEntity.ok().body(eventService.get(uuid));
    }

    @PutMapping
    public ResponseEntity<Event> updateEvent(@RequestBody Event event) {
        return ResponseEntity.status(HttpStatus.OK).body(eventService.create(event));
    }

    @PutMapping("/")
    public ResponseEntity<List<Event>> updateEvents(@RequestBody List<Event> events) {
        return ResponseEntity.status(HttpStatus.OK).body(eventService.create(events));
    }

    @DeleteMapping
    public ResponseEntity<Event> deleteEvent(@RequestParam("uuid") UUID uuid) {
        eventService.delete(uuid);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/")
    public ResponseEntity<List<Event>> deleteEvents(@RequestParam("uuids") UUID uuids) {
        eventService.delete(uuids);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<?> handleEventNotFound(EventNotFoundException exc) {
        return ResponseEntity.badRequest().body(exc.getMessage());
    }
}
