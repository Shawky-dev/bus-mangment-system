package com.habbypanda.bus_mangment_system.route.TimeSlot;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/timeSlot")
@RequiredArgsConstructor
public class TimeSlotController {
    private final TimeSlotService timeSlotService;
    @PostMapping("/create")
    public ResponseEntity<TimeSlotResponse> createTimeSlot(@RequestBody TimeSlotRequest request) {
        TimeSlotResponse response = timeSlotService.createTimeSlot(
                request.getTimeSlot()
        );
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @GetMapping("/getAllTimeSlots")
    public ResponseEntity<TimeSlotResponse> getAllTimeSlots() {
        TimeSlotResponse response = timeSlotService.getAllTimeSlots();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @GetMapping("/getTimeSlot/{timeSlotId}")
    public ResponseEntity<TimeSlotResponse> getTimeSlot(@PathVariable Integer timeSlotId) {
        TimeSlotResponse response = timeSlotService.getTimeSlot(timeSlotId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @DeleteMapping("/deleteTimeSlot/{timeSlotId}")
    public ResponseEntity<TimeSlotResponse> deleteTimeSlot(@PathVariable Integer timeSlotId) {
        TimeSlotResponse response = timeSlotService.deleteTimeSlot(timeSlotId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
