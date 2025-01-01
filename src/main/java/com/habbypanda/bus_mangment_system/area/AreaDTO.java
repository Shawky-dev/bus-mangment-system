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
public class AreaDTO {
    private Integer id;
    private String name;
    private List<StudentDTO> students;
    private List<RouteDTO> routes;
    private List<StopDTO> stopLocations;

    public AreaDTO(Integer id, String name, List<StudentDTO> students, List<RouteDTO> routes, List<StopDTO> stopLocations) {
        this.id = id;
        this.name = name;
        this.students = students;
        this.routes = routes;
        this.stopLocations = stopLocations;
    }

    public AreaDTO(Area area) {
        this.id = area.getId();
        this.name = area.getName();
        this.students = Optional.ofNullable(area.getStudents())
                .map(list -> list.stream().map(StudentDTO::new).collect(Collectors.toList()))
                .orElse(null);
        this.routes = Optional.ofNullable(area.getRoutes())
                .map(list -> list.stream().map(RouteDTO::new).collect(Collectors.toList()))
                .orElse(null);
        this.stopLocations = Optional.ofNullable(area.getStopLocations())
                .map(list -> list.stream().map(StopDTO::new).collect(Collectors.toList()))
                .orElse(null);
    }
}