package com.habbypanda.bus_mangment_system.route;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Response class for Route-related operations.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RouteResponse {
    private String message;
    private HttpStatus status;
    //for single route
    private Route route;
    //for multiple routes
    private List<Route> routes;
}
