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
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.createEvent(event));
    }

    @PostMapping("/")
    public ResponseEntity<List<Event>> createEvents(@RequestBody List<Event> events) {
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.createEvents(events));
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok().body(eventService.getAllEvents());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Event> getEvent(@PathVariable("uuid") UUID uuid) {
        return ResponseEntity.ok().body(eventService.getEvent(uuid));
    }

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<?> handleEventNotFound(EventNotFoundException exc) {
        return ResponseEntity.badRequest().body(exc.getMessage());
    }
}
