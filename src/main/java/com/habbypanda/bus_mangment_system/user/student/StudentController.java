package com.habbypanda.bus_mangment_system.user.student;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @GetMapping("/getAllStudents")
    public ResponseEntity<StudentResponse> getAllStudents() {
        StudentResponse response = studentService.getAllStudents();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @PutMapping("/selectStop")
    public ResponseEntity<StudentResponse> selectStop(@RequestParam Integer studentId, @RequestParam Integer stopId) {
        StudentResponse response = studentService.selectStop(studentId, stopId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @PutMapping("/selectAreaStopRoute")
    public ResponseEntity<StudentResponse> selectAreaStopRoute(@RequestParam Integer studentId, @RequestParam Integer areaId, @RequestParam Integer stopId, @RequestParam Integer routeId) {
        StudentResponse response = studentService.selectAreaStopRoute(studentId, areaId, stopId, routeId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
