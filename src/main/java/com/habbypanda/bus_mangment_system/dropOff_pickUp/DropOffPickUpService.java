package com.habbypanda.bus_mangment_system.dropOff_pickUp;

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
public class DropOffPickUpService {

    private final DropOffPickUpRepository dropOffPickUpRepository;
    private final AreaRepository areaRepository;

    // Create a new DropOffPickUp location for an area
    public DropOffPickUpResponse createLocation(Integer areaId, String locationName) {
        Optional<Area> optionalArea = areaRepository.findById(areaId);
        if (optionalArea.isEmpty()) {
            return DropOffPickUpResponse.builder()
                    .message("Area not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        Area area = optionalArea.get();

        if (locationName == null || locationName.trim().isEmpty()) {
            return DropOffPickUpResponse.builder()
                    .message("Location name cannot be empty")
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }

        DropOffPickUp location = DropOffPickUp.builder()
                .locationName(locationName)
                .area(area)
                .build();

        dropOffPickUpRepository.save(location);

        return DropOffPickUpResponse.builder()
                .message("Location created successfully")
                .status(HttpStatus.CREATED)
                .location(location)
                .build();
    }



    // Get all locations for a specific area
    public DropOffPickUpResponse getLocationsByArea(Integer areaId) {
        boolean areaExists = areaRepository.existsById(areaId);
        if (!areaExists) {
            return DropOffPickUpResponse.builder()
                    .message("Area not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        List<DropOffPickUp> locations = dropOffPickUpRepository.findByAreaId(areaId);

        if (locations.isEmpty()) {
            return DropOffPickUpResponse.builder()
                    .message("No locations found for this area")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        return DropOffPickUpResponse.builder()
                .message("Locations retrieved successfully")
                .status(HttpStatus.OK)
                .locations(locations)
                .build();
    }

    // Delete a DropOffPickUp location
    public DropOffPickUpResponse deleteLocation(Integer areaId, Integer locationId) {
        // Validate if the Area exists
        Optional<Area> optionalArea = areaRepository.findById(areaId);
        if (optionalArea.isEmpty()) {
            return DropOffPickUpResponse.builder()
                    .message("Area not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        Area area = optionalArea.get();

        // Validate if the location exists within the specified Area
        Optional<DropOffPickUp> optionalLocation = dropOffPickUpRepository.findById(locationId);
        if (optionalLocation.isEmpty() || !optionalLocation.get().getArea().getId().equals(areaId)) {
            return DropOffPickUpResponse.builder()
                    .message("Location not found in the specified area")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        dropOffPickUpRepository.deleteById(locationId);

        return DropOffPickUpResponse.builder()
                .message("Location deleted successfully from the specified area")
                .status(HttpStatus.OK)
                .build();
    }

}
