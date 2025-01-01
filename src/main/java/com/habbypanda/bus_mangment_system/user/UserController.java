package com.habbypanda.bus_mangment_system.user;

import com.habbypanda.bus_mangment_system.user.admin.AdminDTO;
import com.habbypanda.bus_mangment_system.user.driver.DriverDTO;
import com.habbypanda.bus_mangment_system.user.parent.ParentDTO;
import com.habbypanda.bus_mangment_system.user.student.StudentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dev/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    @GetMapping("/getAllUsers")
    public ResponseEntity<UserResponseList> getAllUsersByType(@RequestParam Type type) {
        UserResponseList response = userService.getAllUsersByType(type);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/getUserById")
    public ResponseEntity<UserResponse> getUserById(@RequestParam Integer id, Type type) {
        UserResponse response = userService.getUserById(id,type);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/updateStudent")
    public ResponseEntity<UserResponse> updateStudent(@RequestBody StudentDTO studentDTO) {
        UserResponse response = userService.updateStudent(studentDTO);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/updateParent")
    public ResponseEntity<UserResponse> updateParent(@RequestBody ParentDTO parentDTO) {
        UserResponse response = userService.updateParent(parentDTO);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/updateAdmin")
    public ResponseEntity<UserResponse> updateAdmin(@RequestBody AdminDTO adminDTO) {
        UserResponse response = userService.updateAdmin(adminDTO);
        return ResponseEntity.ok(response);
    }
@PutMapping("/updateDriver")
    public ResponseEntity<UserResponse> updateDriver(@RequestBody DriverDTO driverDTO) {
        UserResponse response = userService.updateDriver(driverDTO);
        return ResponseEntity.ok(response);
    }
}
