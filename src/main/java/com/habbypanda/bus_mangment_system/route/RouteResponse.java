package com.habbypanda.bus_mangment_system.route;

import com.habbypanda.bus_mangment_system.utils.DTOMapper;
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
    private RouteDTO route;
    //for multiple routes
    private List<RouteDTO> routes;

    // Constructor for single Route using RouteDTO
    public RouteResponse(String message, HttpStatus status, Route route) {
        this.message = message;
        this.status = status;
        //use DTOMapper to convert Route to RouteDTO
        this.route = DTOMapper.toRouteDTO(route);
    }

    // Constructor for list of Routes using List<RouteDTO>
    public RouteResponse(String message, HttpStatus status, List<Route> routes) {
        this.message = message;
        this.status = status;
        //use DTOMapper to convert List<Route> to List<RouteDTO>
        this.routes = DTOMapper.toRouteDTOList(routes);
    }
}
