package com.kevinthelago.pickle_ball.service;

import com.kevinthelago.pickle_ball.dao.Location;
import com.kevinthelago.pickle_ball.exception.LocationNotFoundException;
import com.kevinthelago.pickle_ball.repo.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    public List<Location> createLocations(List<Location> locations) {
        return locationRepository.saveAll(locations);
    }

    public Location getLocation(UUID uuid) {
        Optional<Location> optionalLocation = locationRepository.findById(uuid);

        if (optionalLocation.isEmpty()) throw new LocationNotFoundException("Location with UUID: " + uuid + " not found.");

        return optionalLocation.get();
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location updateLocation(Location location) {
        return locationRepository.save(location);
    }

    public void deleteLocation(UUID uuid) {
        locationRepository.deleteById(uuid);
    }

    public void deleteAllLocations() {
        locationRepository.deleteAll();
    }
}
