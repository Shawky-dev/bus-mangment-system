package com.habbypanda.bus_mangment_system.user;

import com.habbypanda.bus_mangment_system.user.driver.DriverRepository;
import com.habbypanda.bus_mangment_system.user.parent.ParentRepository;
import com.habbypanda.bus_mangment_system.user.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final StudentRepository studentRepository;
    private final DriverRepository driverRepository;
    private final ParentRepository parentRepository;

    public <T> UserResponse<T> getAllUsers(Type type) {
        List<T> users;
        switch (type) {
            case STUDENT:
                users = (List<T>) studentRepository.findAll();
                break;
            case DRIVER:
                users = (List<T>) driverRepository.findAll();
                break;
            case PARENT:
                users = (List<T>) parentRepository.findAll();
                break;
            default:
                return UserResponse.<T>builder()
                        .message("Invalid user type")
                        .status(HttpStatus.BAD_REQUEST)
                        .build();
        }

        return UserResponse.<T>builder()
                .message("Users retrieved successfully")
                .users(users)
                .status(HttpStatus.OK)
                .build();
    }
    public <T> UserResponse<T> getUserByID(Type type, Integer id) {
        T user;
        switch (type) {
            case STUDENT:
                user = (T) studentRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Student not found"));
                break;
            case DRIVER:
                user = (T) driverRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Driver not found"));
                break;
            case PARENT:
                user = (T) parentRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Parent not found"));
                break;
            default:
                return UserResponse.<T>builder()
                        .message("Invalid user type")
                        .status(HttpStatus.BAD_REQUEST)
                        .build();
        }

        return UserResponse.<T>builder()
                .message("User retrieved successfully")
                .users(List.of(user))
                .status(HttpStatus.OK)
                .build();
    }
}