package com.habbypanda.bus_mangment_system.user.admin;

import com.habbypanda.bus_mangment_system.user.Role;
import com.habbypanda.bus_mangment_system.user.UserDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class AdminDTO extends UserDTO {

    public AdminDTO(Integer id, String name, String email, Role role) {
        super(id, name, email, role);
    }

    public AdminDTO(Admin admin) {
        super(admin.getId(), admin.getName(), admin.getEmail(), admin.getRole());
    }
}