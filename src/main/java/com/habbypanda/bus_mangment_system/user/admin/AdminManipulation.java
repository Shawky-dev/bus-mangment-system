package com.habbypanda.bus_mangment_system.user.admin;

import com.habbypanda.bus_mangment_system.user.UserManipulation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdminManipulation extends UserManipulation<Admin> {
    public AdminManipulation(AdminRepository adminRepository) {
        super(adminRepository);
    }

    public Admin updateAdminFromDTO(Admin admin, AdminDTO adminDTO) {
        Admin newAdmin = Admin.builder()
                .id(admin.getId())
                .name(adminDTO.getName())
                .email(adminDTO.getEmail())
                .password(admin.getPassword())
                .build();
        log.info("Admin updated successfully");
        return newAdmin;
    }
}