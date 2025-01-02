package com.habbypanda.bus_mangment_system.stop;

import com.habbypanda.bus_mangment_system.area.Area;
import com.habbypanda.bus_mangment_system.area.AreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service for managing DropOffPickUp stops.
 */
@Service
@RequiredArgsConstructor
public class StopService {

    private final StopRepository stopRepository;
    private final AreaRepository areaRepository;

    // Create a new DropOffPickUp stop for an area
    public StopResponse createStop(Integer areaId, String stopName) {
        Optional<Area> optionalArea = areaRepository.findById(areaId);
        if (optionalArea.isEmpty()) {
            return StopResponse.builder()
                    .message("Area not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        Area area = optionalArea.get();

        if (stopName == null || stopName.trim().isEmpty()) {
            return StopResponse.builder()
                    .message("stop name cannot be empty")
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }

        Stop stop = Stop.builder()
                .stopName(stopName)
                .area(area)
                .build();

        stopRepository.save(stop);

        return StopResponse.builder()
                .message("stop created successfully")
                .status(HttpStatus.CREATED)
                .stop(stop)
                .build();
    }



    // Get all stops for a specific area
    public StopResponse getStopsByArea(Integer areaId) {
        boolean areaExists = areaRepository.existsById(areaId);
        if (!areaExists) {
            return StopResponse.builder()
                    .message("Area not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        List<Stop> stops = stopRepository.findByAreaId(areaId);

        if (stops.isEmpty()) {
            return StopResponse.builder()
                    .message("No stops found for this area")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        return StopResponse.builder()
                .message("stops retrieved successfully")
                .status(HttpStatus.OK)
                .stops(stops)
                .build();
    }

    // Delete a DropOffPickUp stop
    public StopResponse deleteStop(Integer areaId, Integer stopId) {
        // Validate if the Area exists
        Optional<Area> optionalArea = areaRepository.findById(areaId);
        if (optionalArea.isEmpty()) {
            return StopResponse.builder()
                    .message("Area not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        Area area = optionalArea.get();

        // Validate if the stop exists within the specified Area
        Optional<Stop> optionalStop = stopRepository.findById(stopId);
        if (optionalStop.isEmpty() || !optionalStop.get().getArea().getId().equals(areaId)) {
            return StopResponse.builder()
                    .message("stop not found in the specified area")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        stopRepository.deleteById(stopId);

        return StopResponse.builder()
                .message("stop deleted successfully from the specified area")
                .status(HttpStatus.OK)
                .build();
    }

}
