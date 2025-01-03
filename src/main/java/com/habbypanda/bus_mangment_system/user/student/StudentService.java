package com.habbypanda.bus_mangment_system.user.student;

import com.habbypanda.bus_mangment_system.stop.Stop;
import com.habbypanda.bus_mangment_system.stop.StopRepository;
import com.habbypanda.bus_mangment_system.utils.DTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StopRepository stopRepository;
    public StudentResponse getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return new StudentResponse("All students", HttpStatus.OK, students);
    }

    public StudentResponse selectStop(Integer studentId, Integer stopId) {
        if (!studentRepository.existsById(studentId)) {
            return new StudentResponse("Student not found", HttpStatus.NOT_FOUND);
        }
        if (!stopRepository.existsById(stopId)) {
            return new StudentResponse("Stop not found", HttpStatus.NOT_FOUND);
        }
        Stop stop = stopRepository.findById(stopId).orElseThrow(() -> new RuntimeException("Stop not found"));
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        student.setStop(stop);
        studentRepository.save(student);
        return new StudentResponse("Stop selected successfully", HttpStatus.OK, student);
    }
}