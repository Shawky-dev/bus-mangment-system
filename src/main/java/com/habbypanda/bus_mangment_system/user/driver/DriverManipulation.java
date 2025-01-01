package com.habbypanda.bus_mangment_system.user.driver;

import com.habbypanda.bus_mangment_system.user.UserManipulation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DriverManipulation extends UserManipulation<Driver> {
    public DriverManipulation(DriverRepository driverRepository) {
        super(driverRepository);
    }

    public Driver updateDriverFromDTO(Driver driver, DriverDTO driverDTO) {
        Driver newDriver = Driver.builder()
                .id(driver.getId())
                .name(driverDTO.getName())
                .email(driverDTO.getEmail())
                .password(driver.getPassword())
                .build();
        log.info("Driver updated successfully");
        return newDriver;
    }
}