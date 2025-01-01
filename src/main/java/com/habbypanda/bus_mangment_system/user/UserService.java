package com.habbypanda.bus_mangment_system.user;

import com.habbypanda.bus_mangment_system.user.admin.Admin;
import com.habbypanda.bus_mangment_system.user.admin.AdminDTO;
import com.habbypanda.bus_mangment_system.user.admin.AdminManipulation;
import com.habbypanda.bus_mangment_system.user.driver.Driver;
import com.habbypanda.bus_mangment_system.user.driver.DriverDTO;
import com.habbypanda.bus_mangment_system.user.driver.DriverManipulation;
import com.habbypanda.bus_mangment_system.user.parent.Parent;
import com.habbypanda.bus_mangment_system.user.parent.ParentDTO;
import com.habbypanda.bus_mangment_system.user.parent.ParentManipulation;
import com.habbypanda.bus_mangment_system.user.student.Student;
import com.habbypanda.bus_mangment_system.user.student.StudentDTO;
import com.habbypanda.bus_mangment_system.user.student.StudentManipulation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final StudentManipulation studentManip;
    private final DriverManipulation driverManip;
    private final ParentManipulation parentManip;
    private final AdminManipulation adminManip;

    public UserResponseList getAllUsersByType(Type type) {
        List<? extends User> userList;
        String message;

        switch (type) {
            case STUDENT:
                userList = studentManip.findAll();
                message = "Students fetched successfully";
                break;
            case DRIVER:
                userList = driverManip.findAll();
                message = "Drivers fetched successfully";
                break;
            case PARENT:
                userList = parentManip.findAll();
                message = "Parents fetched successfully";
                break;
            case ADMIN:
                userList = adminManip.findAll();
                message = "Admins fetched successfully";
                break;
            default:
                return new UserResponseList("Invalid type", HttpStatus.BAD_REQUEST, userList = null);
        }
        return new UserResponseList(message, HttpStatus.OK, userList);
    }
    public UserResponse getUserById(Integer id, Type type) {
        User user;
        String message;
        try {
            switch (type) {
                case STUDENT:
                    user = studentManip.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
                    message = "Student fetched successfully";
                    break;
                case DRIVER:
                    user = driverManip.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
                    message = "Driver fetched successfully";
                    break;
                case PARENT:
                    user = parentManip.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
                    message = "Parent fetched successfully";
                    break;
                case ADMIN:
                    user = adminManip.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
                    message = "Admin fetched successfully";
                    break;
                default:
                    return new UserResponse("Invalid type", HttpStatus.BAD_REQUEST, null);
            }
        } catch (IllegalArgumentException e) {
            return new UserResponse(e.getMessage(), HttpStatus.NOT_FOUND, null);
        }
        return new UserResponse(message, HttpStatus.OK, user);
    }
    public UserResponse updateStudent(StudentDTO studentDTO) {
           Student student = studentManip.findById(studentDTO.getId()).orElseThrow(() -> new IllegalArgumentException("Student not found"));
        Student newStudent = studentManip.updateStudentFromDTO(student, studentDTO);
        studentManip.save(newStudent);
        return new UserResponse("Student updated successfully", HttpStatus.OK, newStudent);
    }
    public UserResponse updateParent(ParentDTO parentDTO) {
           Parent parent = parentManip.findById(parentDTO.getId()).orElseThrow(() -> new IllegalArgumentException("Parent not found"));
        Parent newParent = parentManip.updateParentFromDTO(parent, parentDTO);
        parentManip.save(newParent);
        return new UserResponse("Parent updated successfully", HttpStatus.OK, newParent);
    }
    public UserResponse updateAdmin(AdminDTO adminDTO) {
           Admin admin  = adminManip.findById(adminDTO.getId()).orElseThrow(() -> new IllegalArgumentException("Admin not found"));
        Admin newAdmin = adminManip.updateAdminFromDTO(admin, adminDTO);
        adminManip.save(newAdmin);
        return new UserResponse("Admin updated successfully", HttpStatus.OK, newAdmin);
    }
    public UserResponse updateDriver(DriverDTO driverDTO) {
           Driver driver  = driverManip.findById(driverDTO.getId()).orElseThrow(() -> new IllegalArgumentException("Driver not found"));
        Driver newDriver = driverManip.updateDriverFromDTO(driver, driverDTO);
        driverManip.save(newDriver);
        return new UserResponse("Driver updated successfully", HttpStatus.OK, newDriver);
    }
}