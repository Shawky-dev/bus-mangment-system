package com.habbypanda.bus_mangment_system.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dev/user")
@RequiredArgsConstructor
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
//    @PutMapping("/updateUserById")
//    public ResponseEntity<UserResponse> updateUserById(@RequestParam Integer id, Type type, @RequestBody UserDTO userDTO) {
//        UserResponse response = userService.updateUserById(id, type, userDTO);
//        return ResponseEntity.ok(response);
//    }
}
