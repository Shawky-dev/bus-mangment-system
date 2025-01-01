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

import java.util.List;
import java.util.stream.Collectors;


@Data
public class UserResponseList {
    private String message;
    private HttpStatus status;
    private List<UserDTO> users;

    public UserResponseList(String message, HttpStatus status, List<? extends User> users) {
        this.message = message;
        this.status = status;
        this.users = users.stream().map(user -> {
            if (user instanceof Student) {
                return new StudentDTO((Student) user);
            } else if (user instanceof Driver) {
                return new DriverDTO((Driver) user);
            } else if (user instanceof Parent) {
                return new ParentDTO((Parent) user);
            } else if (user instanceof Admin) {
                return new AdminDTO((Admin) user);
            } else {
                throw new IllegalArgumentException("Unknown user type");
            }
        }).collect(Collectors.toList());
    }
}