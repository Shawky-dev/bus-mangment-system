package com.habbypanda.bus_mangment_system.user.driver;

import com.habbypanda.bus_mangment_system.user.UserManipulation;
import org.springframework.stereotype.Service;

@Service
public class DriverManipulation extends UserManipulation<Driver> {
    public DriverManipulation(DriverRepository driverRepository) {
        super(driverRepository);
    }
}
