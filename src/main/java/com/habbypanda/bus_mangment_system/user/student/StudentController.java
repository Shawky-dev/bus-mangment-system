package com.habbypanda.bus_mangment_system.user.student;

import com.habbypanda.bus_mangment_system.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @GetMapping("/getAllStudents")
    public ResponseEntity<UserResponse<Student>> getAllStudents() {
        UserResponse<Student> response = studentService.getAllStudents();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @PutMapping("/selectStop")
    public ResponseEntity<UserResponse<Student>> selectStop(@RequestParam Integer studentId, @RequestParam Integer stopId) {
        UserResponse<Student> response = studentService.selectStop(studentId, stopId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
