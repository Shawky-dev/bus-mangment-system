package com.habbypanda.bus_mangment_system.stop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for managing DropOffPickUp location requests.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StopRequest {
    private String stopName; // Name of the location (e.g., Gate 1, Street 5)
    private Integer areaId;      // ID of the associated area
}
