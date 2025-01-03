package com.habbypanda.bus_mangment_system.auth;

import com.habbypanda.bus_mangment_system.user.*;
import com.habbypanda.bus_mangment_system.user.admin.Admin;
import com.habbypanda.bus_mangment_system.user.admin.AdminDTO;
import com.habbypanda.bus_mangment_system.user.driver.Driver;
import com.habbypanda.bus_mangment_system.user.driver.DriverDTO;
import com.habbypanda.bus_mangment_system.user.parent.Parent;
import com.habbypanda.bus_mangment_system.user.parent.ParentDTO;
import com.habbypanda.bus_mangment_system.user.student.Student;
import com.habbypanda.bus_mangment_system.user.student.StudentDTO;
import com.habbypanda.bus_mangment_system.utils.DTOMapper;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class UserResponse<T> {
    private String message;
    private HttpStatus status;
    private T user;

    public UserResponse(String message, HttpStatus status, User user) {
        this.message = message;
        this.status = status;
        this.user = convertToDTO(user);
    }

    @SuppressWarnings("unchecked")
    private T convertToDTO(User user) {
        if (user instanceof Student) {
            return (T) DTOMapper.toStudentDTO((Student) user);
        } else if (user instanceof Parent) {
            return (T) DTOMapper.toParentDTO((Parent) user);
        } else if (user instanceof Driver) {
            return (T) DTOMapper.toDriverDTO((Driver) user);
        } else if (user instanceof Admin) {
            return (T) DTOMapper.toAdminDTO((Admin) user);
        } else {
            throw new RuntimeException("Unknown user type");
        }
    }
}