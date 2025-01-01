package com.habbypanda.bus_mangment_system.user.student;

import com.habbypanda.bus_mangment_system.user.Role;
import com.habbypanda.bus_mangment_system.user.UserDTO;
import lombok.*;

import java.util.Optional;

import static org.aspectj.weaver.ResolvedTypeMunger.Parent;

@Getter
@Setter
public class StudentDTO extends UserDTO {
    private Integer parentId;
    private Integer areaId;

    public StudentDTO(Integer id, String name, String email, Role role, Integer parentId, Integer areaId) {
        super(id, name, email, role);
        this.parentId = parentId;
        this.areaId = areaId;
    }

    public StudentDTO(Student student) {
        super(student.getId(), student.getName(), student.getEmail(), student.getRole());
        this.parentId = (student.getParent() != null) ? student.getParent().getId() : null;
        this.areaId = (student.getArea() != null) ? student.getArea().getId() : null;
    }
}