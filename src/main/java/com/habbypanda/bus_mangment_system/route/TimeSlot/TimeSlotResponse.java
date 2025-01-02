package com.habbypanda.bus_mangment_system.route.TimeSlot;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimeSlotResponse {
    private String message;
    private HttpStatus status;
    private TimeSlot timeSlot; // For single location operations
    private List<TimeSlot> timeSlots;
}
