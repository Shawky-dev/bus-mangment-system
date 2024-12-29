package com.habbypanda.bus_mangment_system.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("dev/user")
@RequiredArgsConstructor
public class UserController
{
    private final UserService userService;
    @GetMapping("/getAllUsers")
    public ResponseEntity<UserResponse> addStudentToArea(@RequestParam("type") Type type) {
        UserResponse response = userService.getAllUsers(type);

        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
