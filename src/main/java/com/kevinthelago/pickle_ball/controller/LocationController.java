package com.kevinthelago.pickle_ball.controller;

import com.kevinthelago.pickle_ball.dao.Location;
import com.kevinthelago.pickle_ball.exception.CourtNotFoundException;
import com.kevinthelago.pickle_ball.exception.LocationNotFoundException;
import com.kevinthelago.pickle_ball.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/locations")
public class LocationController {
    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        return ResponseEntity.ok().body(locationService.createLocation(location));
    }

    @ExceptionHandler(LocationNotFoundException.class)
    public ResponseEntity<?> handleEventNotFound(LocationNotFoundException exc) {
        return ResponseEntity.badRequest().body(exc.getMessage());
    }
}
