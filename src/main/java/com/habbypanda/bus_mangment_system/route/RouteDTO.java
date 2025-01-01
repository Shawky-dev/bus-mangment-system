package com.habbypanda.bus_mangment_system.route;

import com.habbypanda.bus_mangment_system.stop.StopDTO;
import com.habbypanda.bus_mangment_system.user.student.StudentDTO;
import com.habbypanda.bus_mangment_system.area.Area;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class RouteDTO {
    private Integer id;
    private LocalDate date;
    private LocalTime timeSlot;
    private RouteType type;
    private Integer areaId;
    private List<StudentDTO> students;
    private List<StopDTO> stops;

    public RouteDTO(Integer id, LocalDate date, LocalTime timeSlot, RouteType type, Integer areaId, List<StudentDTO> students, List<StopDTO> stops) {
        this.id = id;
        this.date = date;
        this.timeSlot = timeSlot;
        this.type = type;
        this.areaId = areaId;
        this.students = students;
        this.stops = stops;
    }

    public RouteDTO(Route route) {
        this.id = route.getId();
        this.date = route.getDate();
        this.timeSlot = route.getTimeSlot();
        this.type = route.getType();
        this.areaId = Optional.ofNullable(route.getArea())
                .map(Area::getId)
                .orElse(null);
        this.students = Optional.ofNullable(route.getStudents())
                .map(list -> list.stream().map(StudentDTO::new).collect(Collectors.toList()))
                .orElse(null);
        this.stops = Optional.ofNullable(route.getStops())
                .map(list -> list.stream().map(StopDTO::new).collect(Collectors.toList()))
                .orElse(null);
    }
}