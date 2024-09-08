package com.kevinthelago.pickle_ball.service;

import com.kevinthelago.pickle_ball.dao.Court;
import com.kevinthelago.pickle_ball.dao.Location;
import com.kevinthelago.pickle_ball.repo.CourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CourtService {
    private final CourtRepository courtRepository;

    @Autowired
    public CourtService(CourtRepository courtRepository) {
        this.courtRepository = courtRepository;
    }

    public Court createCourt(Court court) {
        return courtRepository.save(court);
    }

    public List<Court> createCourts(List<Court> courts) {
        return courtRepository.saveAll(courts);
    }

    public List<Court> getAllCourts() {
        return courtRepository.findAll();
    }

    public List<Court> getCourtsByLocation(UUID uuid) {
        return courtRepository.findAllByLocation_Uuid(uuid);
    }

    public Court updateCourt(Court court) {
        return courtRepository.save(court);
    }

    public List<Court> updateCourts(List<Court> courts) {
        return courtRepository.saveAll(courts);
    }

    public void deleteCourt(UUID uuid) {
        courtRepository.deleteById(uuid);
    }

    public void deleteCourts(List<UUID> uuids) {
        courtRepository.deleteAllById(uuids);
    }

    public void deleteAllCourts() {
        courtRepository.deleteAll();
    }
}
