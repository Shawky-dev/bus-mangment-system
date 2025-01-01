package com.habbypanda.bus_mangment_system.stop;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Response class for DropOffPickUp-related operations.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StopResponse {
    private String message;
    private HttpStatus status;
    private Stop location; // For single location operations
    private List<Stop> locations; // For list-based operations
}