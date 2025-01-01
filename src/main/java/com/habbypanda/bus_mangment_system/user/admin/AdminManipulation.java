package com.habbypanda.bus_mangment_system.user.admin;

import com.habbypanda.bus_mangment_system.user.UserManipulation;
import org.springframework.stereotype.Service;

@Service
public class AdminManipulation extends UserManipulation<Admin> {
    public AdminManipulation(AdminRepository adminRepository) {
        super(adminRepository);
    }
}
