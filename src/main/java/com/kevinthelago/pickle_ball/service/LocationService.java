package com.kevinthelago.pickle_ball.service;

import com.kevinthelago.pickle_ball.dao.Address;
import com.kevinthelago.pickle_ball.dao.Location;
import com.kevinthelago.pickle_ball.exception.LocationNotFoundException;
import com.kevinthelago.pickle_ball.repo.AddressRepository;
import com.kevinthelago.pickle_ball.repo.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LocationService {
    private final LocationRepository locationRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository, AddressRepository addressRepository) {
        this.locationRepository = locationRepository;
        this.addressRepository = addressRepository;
    }

    public Location create(Location location) {
        if (location.getAddress() != null) {
            addressRepository.save(location.getAddress());
        }
        return locationRepository.save(location);
    }

    public List<Location> create(List<Location> locations) {
        return locationRepository.saveAll(locations);
    }

    public Location get(UUID uuid) {
        Optional<Location> optionalLocation = locationRepository.findById(uuid);

        if (optionalLocation.isEmpty()) throw new LocationNotFoundException("Location with UUID: " + uuid + " not found.");

        return optionalLocation.get();
    }

    public List<Location> get() {
        return locationRepository.findAll();
    }

    public Location update(Location location) {
        Optional<Location> optionalLocation = locationRepository.findById(location.getUuid());

        if (optionalLocation.isEmpty()) throw new LocationNotFoundException("Location with UUID: " + location.getUuid() + " not found.");

        return locationRepository.save(location);
    }

    public List<Location> update(List<Location> locations) {
        return locations.stream().map(location -> {
            try {
                return locationRepository.save(location);
            } catch (LocationNotFoundException e) {
                return null;
            }
        }).collect(Collectors.toList());
    }

    public void delete(UUID uuid) {
        locationRepository.deleteById(uuid);
    }

    public void delete() {
        locationRepository.deleteAll();
    }
}
