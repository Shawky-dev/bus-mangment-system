package com.habbypanda.bus_mangment_system.area;

import com.habbypanda.bus_mangment_system.user.student.Student;
import com.habbypanda.bus_mangment_system.user.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AreaService {

    private final AreaRepository areaRepository;
    private final StudentRepository studentRepository;

    // Add a student to an area
    public void addStudentToArea(Integer areaId, Integer studentId) {
        Area area = areaRepository.findById(areaId)
                .orElseThrow(() -> new RuntimeException("Area not found"));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        area.addStudent(student);
        areaRepository.save(area);
    }

    // Get all students in an area
    public List<Student> getStudentsInArea(Integer areaId) {
        Area area = areaRepository.findById(areaId)
                .orElseThrow(() -> new RuntimeException("Area not found"));
        return area.getStudents();
    }

    // Create a new Area
    public Area createArea(String name) {
        Area area = Area.builder().name(name).build();
        return areaRepository.save(area);
    }

    // Retrieve all Areas
    public List<Area> getAllAreas() {
        return areaRepository.findAll();
    }
}
