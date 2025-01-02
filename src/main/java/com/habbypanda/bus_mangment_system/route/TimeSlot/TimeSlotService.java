package com.habbypanda.bus_mangment_system.route.TimeSlot;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimeSlotService {
    private final TimeSlotRepository timeSlotRepository;

    public TimeSlotResponse createTimeSlot(String timeSlot) {
        TimeSlot newTimeSlot = TimeSlot.builder().date(timeSlot).build();
        timeSlotRepository.save(newTimeSlot);
        return TimeSlotResponse.builder()
                .message("Time slot created successfully")
                .status(HttpStatus.CREATED)
                .timeSlot(newTimeSlot)
                .build();
    }

    public TimeSlotResponse getTimeSlot(Integer timeSlotId) {
        if (!timeSlotRepository.existsById(timeSlotId)) {
            return TimeSlotResponse.builder()
                    .message("Time slot not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        TimeSlot timeSlot = timeSlotRepository.findById(timeSlotId).orElseThrow(() -> new RuntimeException("Time slot not found"));
        return TimeSlotResponse.builder()
                .message("Time slot retrieved successfully")
                .status(HttpStatus.OK)
                .timeSlot(timeSlot)
                .build();
    }

    public TimeSlotResponse getAllTimeSlots() {
        return TimeSlotResponse.builder()
                .message("Time slots retrieved successfully")
                .status(HttpStatus.OK)
                .timeSlots(timeSlotRepository.findAll())
                .build();
    }
    public TimeSlotResponse deleteTimeSlot(Integer timeSlotId) {
        if (!timeSlotRepository.existsById(timeSlotId)) {
            return TimeSlotResponse.builder()
                    .message("Time slot not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        timeSlotRepository.deleteById(timeSlotId);
        return TimeSlotResponse.builder()
                .message("Time slot deleted successfully")
                .status(HttpStatus.OK)
                .build();
    }

}
