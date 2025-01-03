package com.habbypanda.bus_mangment_system.route;

import com.habbypanda.bus_mangment_system.area.AreaDTO;
import com.habbypanda.bus_mangment_system.route.TimeSlot.TimeSlot;
import com.habbypanda.bus_mangment_system.stop.StopDTO;
import com.habbypanda.bus_mangment_system.user.student.StudentDTO;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RouteDTO {
    private Integer id;
    private LocalDate date;
    private TimeSlot timeSlot;
    private RouteType type;
    private RouteStatus status;
    private Integer areaId;
    private List<StudentDTO> students;
    private List<StopDTO> stops;
}