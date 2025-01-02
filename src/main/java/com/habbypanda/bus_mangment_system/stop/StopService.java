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
            return new StopResponse("Area not found", HttpStatus.NOT_FOUND, null, null);
        }

        Area area = optionalArea.get();

        if (stopName == null || stopName.trim().isEmpty()) {
            return new StopResponse("stop name cannot be empty", HttpStatus.BAD_REQUEST, null, null);
        }

        Stop stop = new Stop(stopName, area);

        stopRepository.save(stop);

        return new StopResponse("stop created successfully", HttpStatus.CREATED, stop, null);
    }

    // Get all stops for a specific area
    public StopResponse getStopsByArea(Integer areaId) {
        boolean areaExists = areaRepository.existsById(areaId);
        if (!areaExists) {
            return new StopResponse("Area not found", HttpStatus.NOT_FOUND, null, null);
        }

        List<Stop> stops = stopRepository.findByAreaId(areaId);

        if (stops.isEmpty()) {
            return new StopResponse("No stops found for this area", HttpStatus.NOT_FOUND, null, null);
        }

        return new StopResponse("stops retrieved successfully", HttpStatus.OK, null, stops);
    }

    // Delete a DropOffPickUp stop
    public StopResponse deleteStop(Integer areaId, Integer stopId) {
        // Validate if the Area exists
        Optional<Area> optionalArea = areaRepository.findById(areaId);
        if (optionalArea.isEmpty()) {
            return new StopResponse("Area not found", HttpStatus.NOT_FOUND, null, null);
        }

        Area area = optionalArea.get();

        // Validate if the stop exists within the specified Area
        Optional<Stop> optionalStop = stopRepository.findById(stopId);
        if (optionalStop.isEmpty() || !optionalStop.get().getArea().getId().equals(areaId)) {
            return new StopResponse("stop not found in the specified area", HttpStatus.NOT_FOUND, null, null);
        }

        stopRepository.deleteById(stopId);

        return new StopResponse("stop deleted successfully from the specified area", HttpStatus.OK, null, null);
    }

}