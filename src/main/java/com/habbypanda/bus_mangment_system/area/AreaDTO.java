package com.habbypanda.bus_mangment_system.area;

import com.habbypanda.bus_mangment_system.route.RouteDTO;
import com.habbypanda.bus_mangment_system.stop.StopDTO;
import com.habbypanda.bus_mangment_system.user.student.StudentDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AreaDTO {
    private Integer id;
    private String name;
    private List<StudentDTO> students;
    private List<RouteDTO> routes;
    private List<StopDTO> stops;

}