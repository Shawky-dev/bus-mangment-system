package com.habbypanda.bus_mangment_system.user.student;

import com.habbypanda.bus_mangment_system.area.AreaDTO;
import com.habbypanda.bus_mangment_system.route.RouteDTO;
import com.habbypanda.bus_mangment_system.stop.StopDTO;
import com.habbypanda.bus_mangment_system.user.Role;
import com.habbypanda.bus_mangment_system.user.parent.Parent;
import com.habbypanda.bus_mangment_system.user.parent.ParentDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTO {
    private Integer id;
    private String name;
    private String email;
    private Role role;
    private Integer parentId;
    private Integer areaId;
    private Integer routeId;
    private Integer stopId;
}