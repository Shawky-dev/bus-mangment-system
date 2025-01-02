package com.habbypanda.bus_mangment_system.stop;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Response class for DropOffPickUp-related operations.
 */
@Data
public class StopResponse {
    private String message;
    private HttpStatus status;
    private StopDTO stop; // For single location operations
    private List<StopDTO> stops;



    public StopResponse(String message, HttpStatus status, Stop stop, List<Stop> stops) {
        this.message = message;
        this.status = status;
        this.stop = new StopDTO(stop);
        this.stops = (stops != null) ? stops.stream()
                .map(StopDTO::new)
                .collect(Collectors.toList())
                : null;
    }
}