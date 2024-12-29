package com.habbypanda.bus_mangment_system.route;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

/**
 * Controller for managing routes.
 */
@RestController
@RequestMapping("/dev/admin/route")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    // Create a new route
    @PostMapping("/createRoute")
    public ResponseEntity<RouteResponse> createRoute(@RequestBody RouteRequest request) {
        RouteResponse response = routeService.createRoute(
                request.getAreaId(),
                request.getDate(),
                request.getTimeSlot(),
                request.getType()
        );
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
    public ResponseEntity<RouteResponse> getRoutesByAreaAndTimeSlot(
            @PathVariable Integer areaId,
            @RequestParam LocalTime timeSlot) {
        RouteResponse response = routeService.getRoutesByAreaAndTimeSlot(areaId, timeSlot);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // Delete a route
    @DeleteMapping("/deleteRoute/{routeId}")
    public ResponseEntity<RouteResponse> deleteRoute(@PathVariable Integer routeId) {
        RouteResponse response = routeService.deleteRoute(routeId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
