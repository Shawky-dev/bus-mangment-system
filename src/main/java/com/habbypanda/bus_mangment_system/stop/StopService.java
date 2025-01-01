package com.habbypanda.bus_mangment_system.stop;

import com.habbypanda.bus_mangment_system.area.Area;
import com.habbypanda.bus_mangment_system.area.AreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service for managing DropOffPickUp locations.
 */
@Service
@RequiredArgsConstructor
public class StopService {

    private final StopRepository stopRepository;
    private final AreaRepository areaRepository;

    // Create a new DropOffPickUp location for an area
    public StopResponse createLocation(Integer areaId, String locationName) {
        Optional<Area> optionalArea = areaRepository.findById(areaId);
        if (optionalArea.isEmpty()) {
            return StopResponse.builder()
                    .message("Area not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        Area area = optionalArea.get();

        if (locationName == null || locationName.trim().isEmpty()) {
            return StopResponse.builder()
                    .message("Location name cannot be empty")
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }

        Stop location = Stop.builder()
                .locationName(locationName)
                .area(area)
                .build();

        stopRepository.save(location);

        return StopResponse.builder()
                .message("Location created successfully")
                .status(HttpStatus.CREATED)
                .location(location)
                .build();
    }



    // Get all locations for a specific area
    public StopResponse getLocationsByArea(Integer areaId) {
        boolean areaExists = areaRepository.existsById(areaId);
        if (!areaExists) {
            return StopResponse.builder()
                    .message("Area not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        List<Stop> locations = stopRepository.findByAreaId(areaId);

        if (locations.isEmpty()) {
            return StopResponse.builder()
                    .message("No locations found for this area")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        return StopResponse.builder()
                .message("Locations retrieved successfully")
                .status(HttpStatus.OK)
                .locations(locations)
                .build();
    }

    // Delete a DropOffPickUp location
    public StopResponse deleteLocation(Integer areaId, Integer locationId) {
        // Validate if the Area exists
        Optional<Area> optionalArea = areaRepository.findById(areaId);
        if (optionalArea.isEmpty()) {
            return StopResponse.builder()
                    .message("Area not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        Area area = optionalArea.get();

        // Validate if the location exists within the specified Area
        Optional<Stop> optionalLocation = stopRepository.findById(locationId);
        if (optionalLocation.isEmpty() || !optionalLocation.get().getArea().getId().equals(areaId)) {
            return StopResponse.builder()
                    .message("Location not found in the specified area")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        stopRepository.deleteById(locationId);

        return StopResponse.builder()
                .message("Location deleted successfully from the specified area")
                .status(HttpStatus.OK)
                .build();
    }

}
