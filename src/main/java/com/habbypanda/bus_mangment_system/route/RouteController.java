package com.habbypanda.bus_mangment_system.route;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/route")
@RequiredArgsConstructor
public class RouteController {
    private final RouteService routeService;
    @PutMapping("/addStopToRoute")
    public ResponseEntity<RouteResponse> addStopToRoute(@RequestParam Integer routeId, @RequestParam Integer stopId, @RequestParam Integer stopOrder) {
        RouteResponse response = routeService.addStopToRoute(routeId, stopId, stopOrder);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
