package com.habbypanda.bus_mangment_system.user;

import com.habbypanda.bus_mangment_system.user.admin.AdminDetailsService;
import com.habbypanda.bus_mangment_system.user.driver.DriverDetailsService;
import com.habbypanda.bus_mangment_system.user.parent.ParentDetailsService;
import com.habbypanda.bus_mangment_system.user.student.StudentDetailsService;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
@Data
public class ComposedDetailsService implements UserDetailsService {
    @Autowired
    private final StudentDetailsService studentDetailsService;
    @Autowired
    private final ParentDetailsService parentDetailsService;
    @Autowired
    private final AdminDetailsService adminDetailsService;
    @Autowired
    private final DriverDetailsService driverDetailsService;

    private List<UserDetailsService> services;
    @PostConstruct
    public void setServices(){
        List<UserDetailsService> new_services = new ArrayList<>();
        new_services.add(this.studentDetailsService);
        new_services.add(this.parentDetailsService);
        new_services.add(this.driverDetailsService);
        new_services.add(this.adminDetailsService);
        this.services = new_services;
    }
    @Override
    public UserDetails loadUserByUsername(String username){
        log.info("Attempting to load user by username: {}", username);
        for (UserDetailsService service : this.services) {
            
            try {
                UserDetails userDetails = service.loadUserByUsername(username);
                log.info("User found in service {}: {}", service.getClass().getSimpleName(), userDetails);
                return userDetails;
            } catch (Exception e) {
                log.info("User not found in service: {}", service.getClass().getSimpleName());
            }
        }
        log.error("User not found: {}", username);
        throw new UsernameNotFoundException("User not found");
    }

}
