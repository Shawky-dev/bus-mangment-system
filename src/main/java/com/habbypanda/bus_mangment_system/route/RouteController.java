package com.habbypanda.bus_mangment_system.route;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

/**
 * Controller for managing routes.
 */
@RestController
@RequestMapping("/route")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    // Create a new route
    @PostMapping("/createRoute")
    public ResponseEntity<RouteResponse> createRoute(@RequestBody RouteRequest request) {
        RouteResponse response = routeService.createRoute(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // Get all routes for a specific area
    @GetMapping("/getRoutesByArea/{areaId}")
    public ResponseEntity<RouteResponse> getRoutesByArea(@PathVariable Integer areaId) {
        RouteResponse response = routeService.getRoutesByArea(areaId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // Get routes by area and time slot
    @GetMapping("/getRoutesByAreaAndTimeSlot/{areaId}")
    public ResponseEntity<RouteResponse> getRoutesByAreaAndTimeSlot(@PathVariable Integer areaId, @RequestParam Integer timeSlotId) {
        RouteResponse response = routeService.getRoutesByAreaAndTimeSlot(areaId, timeSlotId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // Delete a route
    //todo: delete route by area
    @DeleteMapping("/deleteRoute/{routeId}")
    public ResponseEntity<RouteResponse> deleteRoute(@PathVariable Integer routeId) {
        RouteResponse response = routeService.deleteRoute(routeId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    //start route
    @PostMapping("/startRoute/{routeId}")
    public ResponseEntity<RouteResponse> startRoute(@PathVariable Integer routeId) {
        RouteResponse response = routeService.startRoute(routeId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    //complete route
    @PostMapping("/completeRoute/{routeId}")
    public ResponseEntity<RouteResponse> completeRoute(@PathVariable Integer routeId) {
        RouteResponse response = routeService.completeRoute(routeId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // add student to route
    @PostMapping("/addStudentToRoute")
    public ResponseEntity<RouteResponse> addStudentToRoute(@RequestBody RouteStudentRequest request) {
        RouteResponse response = routeService.addStudentToRoute(request.getRouteId(), request.getStudentId());
        return ResponseEntity.status(response.getStatus()).body(response);
    }


}
