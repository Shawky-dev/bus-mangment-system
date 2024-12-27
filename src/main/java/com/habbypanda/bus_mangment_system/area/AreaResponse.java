package com.habbypanda.bus_mangment_system.area;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * Represents a response object for Area-related operations.
 * Used for sending meaningful messages and HTTP status codes back to the client.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AreaResponse {

    /**
     * Message indicating the result of the operation.
     * Example: "Area created successfully", "Area already exists"
     */
    private String message;

    /**
     * HTTP status code representing the result of the operation.
     * Example: HttpStatus.CREATED, HttpStatus.CONFLICT
     */
    private HttpStatus status;

    /**
     * Optionally, include area details if needed.
     */
    private Area area;
}
