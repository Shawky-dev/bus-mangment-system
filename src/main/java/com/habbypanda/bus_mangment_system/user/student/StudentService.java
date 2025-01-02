package com.habbypanda.bus_mangment_system.user.student;

import com.habbypanda.bus_mangment_system.stop.Stop;
import com.habbypanda.bus_mangment_system.stop.StopRepository;
import com.habbypanda.bus_mangment_system.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StopRepository stopRepository;
    public UserResponse<Student> getAllStudents() {
        return new UserResponse<Student>("All students", HttpStatus.OK,studentRepository.findAll());
    }

    public UserResponse<Student> selectStop(Integer studentId, Integer stopId) {
        if (!studentRepository.existsById(studentId)) {
            return new UserResponse<Student>("Student not found", HttpStatus.NOT_FOUND);
        }
        if (!stopRepository.existsById(stopId)) {
            return new UserResponse<Student>("Stop not found", HttpStatus.NOT_FOUND);
        }
        Stop stop = stopRepository.findById(stopId).orElseThrow(() -> new RuntimeException("Stop not found"));
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        student.setStop(stop);
        studentRepository.save(student);
        return new UserResponse<Student>("Stop selected successfully", HttpStatus.OK, student);
    }
}
