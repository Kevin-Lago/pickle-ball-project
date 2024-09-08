package com.kevinthelago.pickle_ball.repo;

import com.kevinthelago.pickle_ball.dao.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LocationRepository extends JpaRepository<Location, UUID> {
}
