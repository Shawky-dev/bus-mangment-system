package com.habbypanda.bus_mangment_system.route;

import com.habbypanda.bus_mangment_system.area.Area;
import com.habbypanda.bus_mangment_system.area.AreaRepository;
import com.habbypanda.bus_mangment_system.route.TimeSlot.TimeSlot;
import com.habbypanda.bus_mangment_system.route.TimeSlot.TimeSlotRepository;
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
    private final TimeSlotRepository timeSlotRepository;

    // Create a new Route for an area
    public RouteResponse createRoute(RouteRequest request) {
        Optional<Area> optionalArea = areaRepository.findById(request.getAreaId());
        if (optionalArea.isEmpty()) {
            return RouteResponse.builder()
                    .message("Area not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        Optional<TimeSlot> optionalTimeSlot = timeSlotRepository.findById(request.getTimeSlotId());
        if (optionalTimeSlot.isEmpty()) {
            return RouteResponse.builder()
                    .message("Time Slot not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        Area area = optionalArea.get();
        TimeSlot timeSlot = optionalTimeSlot.get();

        Route route = Route.builder()
                .date(request.getDate())
                .timeSlot(timeSlot)
                .type(request.getType())
                .area(area)
                .status(RouteStatus.PENDING)
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
    public RouteResponse getRoutesByAreaAndTimeSlot(Integer areaId, Integer timeSlotId) {
        // Validate if the area exists
        boolean areaExists = areaRepository.existsById(areaId);
        if (!areaExists) {
            return RouteResponse.builder()
                    .message("Area not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        Optional<TimeSlot> optionalTimeSlot = timeSlotRepository.findById(timeSlotId);
        if (optionalTimeSlot.isEmpty()) {
            return RouteResponse.builder()
                    .message("Time Slot not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        TimeSlot timeSlot = optionalTimeSlot.get();
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
    public RouteResponse addStudentToRoute(Integer routeId, Integer studentId) {
        // Validate Route
        Optional<Route> optionalRoute = routeRepository.findById(routeId);
        if (optionalRoute.isEmpty()) {
            return RouteResponse.builder()
                    .message("Route not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        Route route = optionalRoute.get();

        // Check Route Status
        if (route.getStatus() != RouteStatus.PENDING) {
            return RouteResponse.builder()
                    .message("Cannot add a student to a route that has already started or completed")
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }

        // Validate Student
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isEmpty()) {
            return RouteResponse.builder()
                    .message("Student not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        Student student = optionalStudent.get();

        // Check if student is already added
        if (route.getStudents().contains(student)) {
            return RouteResponse.builder()
                    .message("Student is already added to this route")
                    .status(HttpStatus.CONFLICT)
                    .build();
        }

        // Add Student to Route
        route.getStudents().add(student);
        routeRepository.save(route);

        return RouteResponse.builder()
                .message("Student added to route successfully")
                .status(HttpStatus.OK)
                .route(route)
                .build();
    }


    //Start Ride
    public RouteResponse startRoute(Integer routeId) {
        Optional<Route> optionalRoute = routeRepository.findById(routeId);
        if (optionalRoute.isEmpty()) {
            return RouteResponse.builder()
                    .message("Route not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        Route route = optionalRoute.get();

        if (route.getStatus() != RouteStatus.PENDING) {
            return RouteResponse.builder()
                    .message("Cannot start a route that is not in PENDING status")
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }

        route.setStatus(RouteStatus.IN_PROGRESS);
        routeRepository.save(route);

        return RouteResponse.builder()
                .message("Route started successfully")
                .status(HttpStatus.OK)
                .route(route)
                .build();
    }


    //End Ride
    public RouteResponse completeRoute(Integer routeId) {
        Optional<Route> optionalRoute = routeRepository.findById(routeId);
        if (optionalRoute.isEmpty()) {
            return RouteResponse.builder()
                    .message("Route not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        Route route = optionalRoute.get();

        if (route.getStatus() != RouteStatus.IN_PROGRESS) {
            return RouteResponse.builder()
                    .message("Cannot complete a route that is not in IN_PROGRESS status")
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }

        route.setStatus(RouteStatus.COMPLETED);
        routeRepository.save(route);

        return RouteResponse.builder()
                .message("Route completed successfully")
                .status(HttpStatus.OK)
                .route(route)
                .build();
    }

}
