package com.kevinthelago.pickle_ball.service;

import com.kevinthelago.pickle_ball.dao.Court;
import com.kevinthelago.pickle_ball.dao.Location;
import com.kevinthelago.pickle_ball.exception.CourtNotFoundException;
import com.kevinthelago.pickle_ball.repo.CourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourtService {
    private final CourtRepository courtRepository;
    private final LocationService locationService;

    @Autowired
    public CourtService(CourtRepository courtRepository, LocationService locationService) {
        this.courtRepository = courtRepository;
        this.locationService = locationService;
    }

    public Court create(Court court) {
        return courtRepository.save(court);
    }

    public List<Court> create(List<Court> courts) {
        return courtRepository.saveAll(courts);
    }

    public List<Court> get() {
        return courtRepository.findAll();
    }

    public Court get(UUID uuid) {
        Optional<Court> optionalCourt = courtRepository.findById(uuid);

        if (optionalCourt.isEmpty()) throw new CourtNotFoundException("Court not found with uuid: " + uuid);

        return optionalCourt.get();
    }

    public List<Court> getByLocation(UUID uuid) {
        return courtRepository.findAllByLocation_Uuid(uuid);
    }

    public Court update(Court court) {
        Optional<Court> optionalCourt = courtRepository.findById(court.getUuid());

        if (optionalCourt.isEmpty()) throw new CourtNotFoundException("Location with UUID: " + court.getUuid() + " not found.");

        return courtRepository.save(court);
    }

    public List<Court> update(List<Court> courts) {
        return courts.stream().map(court -> {
            try {
                return courtRepository.save(court);
            } catch (CourtNotFoundException e) {
                return null;
            }
        }).collect(Collectors.toList());
    }

    public void delete(UUID uuid) {
        courtRepository.deleteById(uuid);
    }

    public void delete(List<UUID> uuids) {
        courtRepository.deleteAllById(uuids);
    }

    public void delete() {
        courtRepository.deleteAll();
    }
}
