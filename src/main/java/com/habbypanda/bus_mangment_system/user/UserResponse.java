package com.habbypanda.bus_mangment_system.user;

import com.habbypanda.bus_mangment_system.user.admin.Admin;
import com.habbypanda.bus_mangment_system.user.admin.AdminDTO;
import com.habbypanda.bus_mangment_system.user.driver.Driver;
import com.habbypanda.bus_mangment_system.user.driver.DriverDTO;
import com.habbypanda.bus_mangment_system.user.parent.Parent;
import com.habbypanda.bus_mangment_system.user.parent.ParentDTO;
import com.habbypanda.bus_mangment_system.user.student.Student;
import com.habbypanda.bus_mangment_system.user.student.StudentDTO;
import lombok.*;
import org.springframework.http.HttpStatus;

@Data
public class UserResponse {
    private String message;
    private HttpStatus status;
    private UserDTO user;

    public UserResponse(String message, HttpStatus status, User user) {
        this.message = message;
        this.status = status;
        if (user instanceof Student) {
            this.user = new StudentDTO((Student) user);
        } else if (user instanceof Driver) {
            this.user = new DriverDTO((Driver) user);
        } else if (user instanceof Parent) {
            this.user = new ParentDTO((Parent) user);
        } else if (user instanceof Admin) {
            this.user = new AdminDTO((Admin) user);
        } else {
            throw new IllegalArgumentException("Unknown user type");
        }
    }
}