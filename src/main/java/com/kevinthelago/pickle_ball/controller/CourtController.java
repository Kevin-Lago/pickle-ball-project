package com.kevinthelago.pickle_ball.controller;

import com.kevinthelago.pickle_ball.dao.Court;
import com.kevinthelago.pickle_ball.exception.CourtNotFoundException;
import com.kevinthelago.pickle_ball.exception.EventNotFoundException;
import com.kevinthelago.pickle_ball.service.CourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/courts")
public class CourtController {
    private final CourtService courtService;

    @Autowired
    public CourtController(CourtService courtService) {
        this.courtService = courtService;
    }

    @PostMapping
    public ResponseEntity<Court> createCourt(@RequestBody Court court) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courtService.create(court));
    }

    @PostMapping("/")
    public ResponseEntity<List<Court>> createCourt(@RequestBody List<Court> courts) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courtService.create(courts));
    }

    @GetMapping
    public ResponseEntity<List<Court>> getAllCourts() {
        return ResponseEntity.ok().body(courtService.get());
    }

    @GetMapping("/{location}")
    public ResponseEntity<List<Court>> getAllCourtsByLocation(@PathVariable("location") UUID uuid) {
        return ResponseEntity.ok().body(courtService.getByLocation(uuid));
    }

    @PutMapping
    public ResponseEntity<Court> updateCourt(@RequestBody Court court) {
        return ResponseEntity.ok().body(courtService.update(court));
    }

    @PutMapping("/")
    public ResponseEntity<List<Court>> update(@RequestBody List<Court> courts) {
        return ResponseEntity.ok().body(courtService.update(courts));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam("uuid") UUID uuid) {
        courtService.delete(uuid);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/")
    public ResponseEntity<?> delete(@RequestParam("uuids") List<UUID> uuid) {
        courtService.delete(uuid);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(CourtNotFoundException.class)
    public ResponseEntity<?> handleEventNotFound(CourtNotFoundException exc) {
        return ResponseEntity.badRequest().body(exc.getMessage());
    }
}
