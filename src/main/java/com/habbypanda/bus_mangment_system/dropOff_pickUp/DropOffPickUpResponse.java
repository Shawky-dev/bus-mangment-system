package com.habbypanda.bus_mangment_system.dropOff_pickUp;

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
public class DropOffPickUpResponse {
    private String message;
    private HttpStatus status;
    private DropOffPickUp location; // For single location operations
    private List<DropOffPickUp> locations; // For list-based operations
}