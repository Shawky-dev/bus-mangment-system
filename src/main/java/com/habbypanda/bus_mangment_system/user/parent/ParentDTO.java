package com.habbypanda.bus_mangment_system.user.parent;

import com.habbypanda.bus_mangment_system.user.Role;
import com.habbypanda.bus_mangment_system.user.UserDTO;
import lombok.*;

@Getter
@Setter
public class ParentDTO extends UserDTO {
    private Integer studentId;

    public ParentDTO(Integer id, String name, String email, Role role, Integer studentId) {
        super(id, name, email, role);
        this.studentId = studentId;
    }

    public ParentDTO(Parent parent) {
        super(parent.getId(), parent.getName(), parent.getEmail(), parent.getRole());
        this.studentId = (parent.getStudent() != null) ? parent.getStudent().getId() : null;
    }
}