package com.habbypanda.bus_mangment_system.user.driver;

import com.habbypanda.bus_mangment_system.user.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DriverDTO {
    private Integer id;
    private String name;
    private String email;
    private Role role;
    private Integer driverLicense;
}