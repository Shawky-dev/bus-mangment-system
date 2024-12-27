package com.habbypanda.bus_mangment_system.area;

import com.habbypanda.bus_mangment_system.user.student.Student;
import com.habbypanda.bus_mangment_system.user.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
            return AreaResponse.builder()
                    .message("Area name cannot be empty")
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }

        area.setName(newName);
        areaRepository.save(area);

        return AreaResponse.builder()
                .message("Area updated successfully")
                .status(HttpStatus.OK)
                .area(area)
                .build();
    }

    // Delete Area
    public AreaResponse deleteArea(Integer areaId) {
        Optional<Area> area = areaRepository.findById(areaId);

        if (area.isEmpty()) {
            return AreaResponse.builder()
                    .message("Area not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }

        areaRepository.deleteById(areaId);

        return AreaResponse.builder()
                .message("Area deleted successfully")
                .status(HttpStatus.OK)
                .build();
    }

    // Remove Student from Area
    public AreaResponse removeStudentFromArea(Integer areaId, Integer studentId) {
        // Fetch Area
        Optional<Area> optionalArea = areaRepository.findById(areaId);
        if (optionalArea.isEmpty()) {
            return AreaResponse.builder()
                    .message("Area not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        Area area = optionalArea.get();

        // Fetch Student
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isEmpty()) {
            return AreaResponse.builder()
                    .message("Student not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        Student student = optionalStudent.get();

        // Check if the student is in the specified area
        if (!area.getStudents().contains(student)) {
            return AreaResponse.builder()
                    .message("Student not found in the specified area")
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }

        // Remove Student from Area
        area.getStudents().remove(student);
        areaRepository.save(area);

        return AreaResponse.builder()
                .message("Student removed from area successfully")
                .status(HttpStatus.OK)
                .area(area)
                .build();
    }


    // Add a student to an area
    public AreaResponse addStudentToArea(Integer areaId, Integer studentId)
    {
        // Fetch Area
        Optional<Area> optionalArea = areaRepository.findById(areaId);
        if (optionalArea.isEmpty()) {
            return AreaResponse.builder()
                    .message("Area not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        Area area = optionalArea.get();

        // Fetch Student
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isEmpty()) {
            return AreaResponse.builder()
                    .message("Student not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        Student student = optionalStudent.get();

        // Check if student already exists in any area
        Optional<Area> studentCurrentArea = areaRepository.findAll()
                .stream()
                .filter(a -> a.getStudents().contains(student))
                .findFirst();

        // Check if student already exists in this specific area
        if (area.getStudents().contains(student)) {
            return AreaResponse.builder()
                    .message("Student is already assigned to this area")
                    .status(HttpStatus.CONFLICT)
                    .area(area)
                    .build();
        }

        if (studentCurrentArea.isPresent()) {
            return AreaResponse.builder()
                    .message("Student is already assigned to another area")
                    .status(HttpStatus.CONFLICT)
                    .area(studentCurrentArea.get())
                    .build();
        }

        // Add Student to Area
        area.addStudent(student);
        areaRepository.save(area);

        return AreaResponse.builder()
                .message("Student added to area successfully")
                .status(HttpStatus.OK)
                .area(area)
                .build();
    }

    // Get all students in an area
    public AreaResponse getStudentsInArea(Integer areaId) {
        // Fetch Area
        Optional<Area> optionalArea = areaRepository.findById(areaId);
        if (optionalArea.isEmpty()) {
            return AreaResponse.builder()
                    .message("Area not found")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
        Area area = optionalArea.get();

        // Return Students in Area
        return AreaResponse.builder()
                .message("Students retrieved successfully")
                .status(HttpStatus.OK)
                .area(area)
                .build();
    }


    // Create a new Area
    public AreaResponse createArea(String name) {
        Optional<Area> existingArea = areaRepository.findByName(name);
        if (existingArea.isPresent()) {
            return AreaResponse.builder()
                    .message("Area already exists")
                    .status(HttpStatus.CONFLICT)
                    .build();
        }
        Area area = Area.builder().name(name).build();
        areaRepository.save(area);
        return AreaResponse.builder()
                .message("Area created successfully")
                .status(HttpStatus.CREATED)
                .area(area)
                .build();
    }

    // Retrieve all Areas
    public List<Area> getAllAreas() {
        return areaRepository.findAll();
    }
}
