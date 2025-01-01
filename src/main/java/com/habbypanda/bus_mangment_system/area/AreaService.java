package com.habbypanda.bus_mangment_system.area;

import com.habbypanda.bus_mangment_system.user.student.Student;
import com.habbypanda.bus_mangment_system.user.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AreaService {

    private final AreaRepository areaRepository;
    private final StudentRepository studentRepository;

    // Edit Area
    public AreaResponse editArea(Integer areaId, String newName) {
        Area area = areaRepository.findById(areaId)
                .orElseThrow(() -> new RuntimeException("Area not found"));

        if (newName == null || newName.trim().isEmpty()) {
            return new AreaResponse("Area name cannot be empty", HttpStatus.BAD_REQUEST, (Area) null);
        }

        area.setName(newName);
        areaRepository.save(area);

        return new AreaResponse("Area updated successfully", HttpStatus.OK, area);
    }

    // Delete Area
    public AreaResponse deleteArea(Integer areaId) {
        Optional<Area> area = areaRepository.findById(areaId);

        if (area.isEmpty()) {
            return new AreaResponse("Area not found", HttpStatus.NOT_FOUND, (Area) null);
        }

        areaRepository.deleteById(areaId);

        return new AreaResponse("Area deleted successfully", HttpStatus.OK, (Area) null);
    }

    // Remove Student from Area
    public AreaResponse removeStudentFromArea(Integer areaId, Integer studentId) {
        // Fetch Area
        Optional<Area> optionalArea = areaRepository.findById(areaId);
        if (optionalArea.isEmpty()) {
            return new AreaResponse("Area not found", HttpStatus.NOT_FOUND, (Area) null);
        }
        Area area = optionalArea.get();

        // Fetch Student
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isEmpty()) {
            return new AreaResponse("Student not found", HttpStatus.NOT_FOUND, (Area) null);
        }
        Student student = optionalStudent.get();

        // Check if the student is in the specified area
        if (!area.getStudents().contains(student)) {
            return new AreaResponse("Student not found in the specified area", HttpStatus.BAD_REQUEST, (Area) null);
        }

        // Remove Student from Area
        area.getStudents().remove(student);
        areaRepository.save(area);

        return new AreaResponse("Student removed from area successfully", HttpStatus.OK, area);
    }

    // Add a student to an area
    public AreaResponse addStudentToArea(Integer areaId, Integer studentId) {
        // Fetch Area
        Optional<Area> optionalArea = areaRepository.findById(areaId);
        if (optionalArea.isEmpty()) {
            return new AreaResponse("Area not found", HttpStatus.NOT_FOUND, (Area) null);
        }
        Area area = optionalArea.get();

        // Fetch Student
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isEmpty()) {
            return new AreaResponse("Student not found", HttpStatus.NOT_FOUND, (Area) null);
        }
        Student student = optionalStudent.get();

        // Check if student already exists in any area
        Optional<Area> studentCurrentArea = areaRepository.findAll()
                .stream()
                .filter(a -> a.getStudents().contains(student))
                .findFirst();

        // Check if student already exists in this specific area
        if (area.getStudents().contains(student)) {
            return new AreaResponse("Student is already assigned to this area", HttpStatus.CONFLICT, area);
        }

        if (studentCurrentArea.isPresent()) {
            return new AreaResponse("Student is already assigned to another area", HttpStatus.CONFLICT, studentCurrentArea.get());
        }

        // Add Student to Area
        area.addStudent(student);
        areaRepository.save(area);

        return new AreaResponse("Student added to area successfully", HttpStatus.OK, area);
    }

    // Get all students in an area
    public AreaResponse getStudentsInArea(Integer areaId) {
        // Fetch Area
        Optional<Area> optionalArea = areaRepository.findById(areaId);
        if (optionalArea.isEmpty()) {
            return new AreaResponse("Area not found", HttpStatus.NOT_FOUND, (Area) null);
        }
        Area area = optionalArea.get();

        // Return Students in Area
        return new AreaResponse("Students retrieved successfully", HttpStatus.OK, area);
    }

    // Create a new Area
    public AreaResponse createArea(String name) {
        Optional<Area> existingArea = areaRepository.findByName(name);
        if (existingArea.isPresent()) {
            return new AreaResponse("Area already exists", HttpStatus.CONFLICT, (Area) null);
        }
        Area area = Area.builder().name(name).build();
        areaRepository.save(area);
        return new AreaResponse("Area created successfully", HttpStatus.CREATED, area);
    }

    // Retrieve all Areas
    public List<AreaDTO> getAllAreas() {
        return areaRepository.findAll().stream()
                .map(AreaDTO::new)
                .collect(Collectors.toList());
    }
}