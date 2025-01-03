package com.habbypanda.bus_mangment_system.user.admin;

import com.habbypanda.bus_mangment_system.user.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminDTO {
    private Integer id;
    private String name;
    private Role role;
    private String email;
}