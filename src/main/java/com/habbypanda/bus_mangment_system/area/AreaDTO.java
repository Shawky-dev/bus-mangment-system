package com.habbypanda.bus_mangment_system.area;

import com.habbypanda.bus_mangment_system.route.RouteDTO;
import com.habbypanda.bus_mangment_system.stop.StopDTO;
import com.habbypanda.bus_mangment_system.user.student.StudentDTO;
import lombok.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class AreaDTO {
    private Integer id;
    private String name;
    private List<StudentDTO> students;
    private List<RouteDTO> routes;
    private List<StopDTO> stop;

    public AreaDTO(Integer id, String name, List<StudentDTO> students, List<RouteDTO> routes, List<StopDTO> stop) {
        this.id = id;
        this.name = name;
        this.students = students;
        this.routes = routes;
        this.stop = stop;
    }

    public AreaDTO(Area area) {
        if(area == null) return;
        this.id = (area.getId() != null) ? area.getId() : null;
        this.name = (area.getName() != null) ? area.getName() : null;
        this.students = Optional.ofNullable(area.getStudents())
                .map(list -> list.stream().map(StudentDTO::new).collect(Collectors.toList()))
                .orElse(null);
        this.routes = Optional.ofNullable(area.getRoutes())
                .map(list -> list.stream().map(RouteDTO::new).collect(Collectors.toList()))
                .orElse(null);
        this.stop = Optional.ofNullable(area.getStops())
                .map(list -> list.stream().map(StopDTO::new).collect(Collectors.toList()))
                .orElse(null);
    }
}