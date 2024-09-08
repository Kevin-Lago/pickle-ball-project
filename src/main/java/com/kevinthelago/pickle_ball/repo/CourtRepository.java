package com.kevinthelago.pickle_ball.repo;

import com.kevinthelago.pickle_ball.dao.Court;
import com.kevinthelago.pickle_ball.dao.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CourtRepository extends JpaRepository<Court, UUID> {
    List<Court> findAllByLocation_Uuid(UUID uuid);
}
