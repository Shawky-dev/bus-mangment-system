package com.habbypanda.bus_mangment_system.route;

import com.habbypanda.bus_mangment_system.area.Area;
import com.habbypanda.bus_mangment_system.area.AreaRepository;
import com.habbypanda.bus_mangment_system.user.student.Student;
import com.habbypanda.bus_mangment_system.user.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * Service class for managing routes.
 */
@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository routeRepository;
    private final AreaRepository areaRepository;
    private final StudentRepository studentRepository;

    // Create a new Route for an area
    public RouteResponse createRoute(Integer areaId, LocalDate date, LocalTime timeSlot, RouteType type) {
        Optional<Area> optionalArea = areaRepository.findById(areaId);
        if (optionalArea.isEmpty()) {
            return RouteResponse.builder()
                    .message("Area not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        Area area = optionalArea.get();

        Route route = Route.builder()
                .date(date)
                .timeSlot(timeSlot)
                .type(type)
                .area(area)
                .build();

        routeRepository.save(route);

        return RouteResponse.builder()
                .message("Route created successfully")
                .status(HttpStatus.CREATED)
                .route(route)
                .build();
    }

    // Retrieve all routes for a specific area
    public RouteResponse getRoutesByArea(Integer areaId) {
        // Validate if the area exists
        boolean areaExists = areaRepository.existsById(areaId);
        if (!areaExists) {
            return RouteResponse.builder()
                    .message("Area not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        List<Route> routes = routeRepository.findByAreaId(areaId);

        if (routes.isEmpty()) {
            return RouteResponse.builder()
                    .message("No routes found for this area")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        return RouteResponse.builder()
                .message("Routes retrieved successfully")
                .status(HttpStatus.OK)
                .routes(routes)
                .build();
    }

    // Retrieve routes by area and time slot
    public RouteResponse getRoutesByAreaAndTimeSlot(Integer areaId, LocalTime timeSlot) {
        // Validate if the area exists
        boolean areaExists = areaRepository.existsById(areaId);
        if (!areaExists) {
            return RouteResponse.builder()
                    .message("Area not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        List<Route> routes = routeRepository.findByAreaIdAndTimeSlot(areaId, timeSlot);

        if (routes.isEmpty()) {
            return RouteResponse.builder()
                    .message("No routes found for the given area and time slot")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        return RouteResponse.builder()
                .message("Routes retrieved successfully")
                .status(HttpStatus.OK)
                .routes(routes)
                .build();
    }

    // Delete a route
    public RouteResponse deleteRoute(Integer routeId) {
        Optional<Route> optionalRoute = routeRepository.findById(routeId);

        if (optionalRoute.isEmpty()) {
            return RouteResponse.builder()
                    .message("Route not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        routeRepository.deleteById(routeId);

        return RouteResponse.builder()
                .message("Route deleted successfully")
                .status(HttpStatus.OK)
                .build();
    }

    // Register a student for a route

    //route statuses set to pending

    //route sttatus set to starting

    //route status set to completed
}
