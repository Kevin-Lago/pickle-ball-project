package com.kevinthelago.pickle_ball.controller;

import com.kevinthelago.pickle_ball.dao.Location;
import com.kevinthelago.pickle_ball.exception.CourtNotFoundException;
import com.kevinthelago.pickle_ball.exception.LocationNotFoundException;
import com.kevinthelago.pickle_ball.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/locations")
public class LocationController {
    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public ResponseEntity<Location> create(@RequestBody Location location) {
        return ResponseEntity.ok().body(locationService.create(location));
    }

    @PostMapping("/")
    public ResponseEntity<List<Location>> create(@RequestBody List<Location> locations) {
        return ResponseEntity.ok().body(locationService.create(locations));
    }

    @GetMapping
    public ResponseEntity<List<Location>> get() {
        return ResponseEntity.ok().body(locationService.get());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Location> get(@PathVariable("uuid") UUID uuid) {
        return ResponseEntity.ok().body(locationService.get(uuid));
    }

    @PutMapping
    public ResponseEntity<Location> update(@RequestBody Location location) {
        return ResponseEntity.ok().body(locationService.update(location));
    }

    @PutMapping("/")
    public ResponseEntity<List<Location>> update(@RequestBody List<Location> locations) {
        return ResponseEntity.ok().body(locationService.update(locations));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> delete(@PathVariable("uuid") UUID uuid) {
        locationService.delete(uuid);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(LocationNotFoundException.class)
    public ResponseEntity<?> handleEventNotFound(LocationNotFoundException exc) {
        return ResponseEntity.badRequest().body(exc.getMessage());
    }
}
