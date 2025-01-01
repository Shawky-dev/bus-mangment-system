package com.habbypanda.bus_mangment_system.user.student;

import com.habbypanda.bus_mangment_system.area.Area;
import com.habbypanda.bus_mangment_system.area.AreaRepository;
import com.habbypanda.bus_mangment_system.user.UserManipulation;
import com.habbypanda.bus_mangment_system.user.parent.Parent;
import com.habbypanda.bus_mangment_system.user.parent.ParentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StudentManipulation extends UserManipulation<Student> {
    private final AreaRepository areaRepository;
    private final ParentRepository parentRepository;
    public StudentManipulation(StudentRepository studentRepository, AreaRepository areaRepository, ParentRepository parentRepository) {
        super(studentRepository);
        this.areaRepository = areaRepository;
        this.parentRepository = parentRepository;
    }
    public Student updateStudentFromDTO(Student student, StudentDTO studentDTO) {
        Parent parent = (studentDTO.getParentId() != null) ? parentRepository.findById(studentDTO.getParentId()).orElse(null) : null;
        Area area = (studentDTO.getAreaId() != null) ? areaRepository.findById(studentDTO.getAreaId()).orElse(null) : null;
        Student newStudent = Student.builder()
                .id(student.getId())
                .name(studentDTO.getName())
                .email(studentDTO.getEmail())
                .password(student.getPassword())
                .parent(parent)
                .area(area)
                .build();
        log.info("Student updated successfully");
        return newStudent;
    }
}

