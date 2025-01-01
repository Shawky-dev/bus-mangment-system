package com.habbypanda.bus_mangment_system.user.driver;

import com.habbypanda.bus_mangment_system.user.Role;
import com.habbypanda.bus_mangment_system.user.UserDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class DriverDTO extends UserDTO {
    private Integer driverLicense;

    public DriverDTO(Integer id, String name, String email, String password, Role role, Integer driverLicense) {
        super(id, name, email, role);
        this.driverLicense = driverLicense;
    }

    public DriverDTO(Driver driver) {
        super(driver.getId(), driver.getName(), driver.getEmail(), driver.getRole());
        this.driverLicense = driver.getDriverLicense();
    }
}