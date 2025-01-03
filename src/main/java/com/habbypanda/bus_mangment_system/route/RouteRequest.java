package com.habbypanda.bus_mangment_system.route;

import com.habbypanda.bus_mangment_system.route.TimeSlot.TimeSlot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * DTO for creating a new route.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RouteRequest {
    private Integer areaId;   // ID of the area the route belongs to
    private LocalDate date;   // Date of the route
    private Integer timeSlotId; // Time slot of the route
    private RouteType type;   // Route type: PICKUP or DROPOFF
}
