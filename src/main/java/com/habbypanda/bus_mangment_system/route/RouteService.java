package com.habbypanda.bus_mangment_system.route;

import com.habbypanda.bus_mangment_system.route.routestop.RouteStop;
import com.habbypanda.bus_mangment_system.route.routestop.RouteStopRepository;
import com.habbypanda.bus_mangment_system.stop.Stop;
import com.habbypanda.bus_mangment_system.stop.StopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;
    private final StopRepository stopRepository;
    private final RouteStopRepository routeStopRepository;
    public RouteResponse addStopToRoute(Integer routeId, Integer stopId, Integer stopOrder) {
        if (!routeRepository.existsById(routeId)) {
            return RouteResponse.builder().message("Route not found").status(HttpStatus.NOT_FOUND).build();
        }
        if (!stopRepository.existsById(stopId)) {
            return RouteResponse.builder().message("Stop not found").status(HttpStatus.NOT_FOUND).build();
        }
        Route route = routeRepository.findById(routeId).orElseThrow(() -> new RuntimeException("Route not found"));
        Stop stop = stopRepository.findById(stopId).orElseThrow(() -> new RuntimeException("Stop not found"));
        RouteStop routeStop = RouteStop.builder().route(route).stop(stop).stopOrder(stopOrder).build();
        routeStopRepository.save(routeStop);
        route.getRouteStops().add(routeStop);
        routeRepository.save(route);
        return RouteResponse.builder().message("Stop added to route successfully").status(HttpStatus.OK).route(route).build();
    }
}
