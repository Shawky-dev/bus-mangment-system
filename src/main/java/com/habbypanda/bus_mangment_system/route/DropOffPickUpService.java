package com.habbypanda.bus_mangment_system.route;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DropOffPickUpService {

    private final DropOffPickUpRepository dropOffPickUpRepository;

    public DropOffPickUp createLocation(String locationName) {
        DropOffPickUp location = DropOffPickUp.builder()
                .locationName(locationName)
                .build();
        return dropOffPickUpRepository.save(location);
    }

    public List<DropOffPickUp> getAllLocations() {
        return dropOffPickUpRepository.findAll();
    }

    public DropOffPickUp getLocationById(UUID id) {
        return dropOffPickUpRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found"));
    }
}
