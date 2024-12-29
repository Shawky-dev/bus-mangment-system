package com.habbypanda.bus_mangment_system.route;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request class for adding a student to a route.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteStudentRequest {
    private Integer routeId;   // ID of the route
    private Integer studentId; // ID of the student
}
