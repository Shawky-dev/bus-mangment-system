package com.habbypanda.bus_mangment_system.user.student;

import com.habbypanda.bus_mangment_system.area.Area;
import com.habbypanda.bus_mangment_system.area.AreaRepository;
import com.habbypanda.bus_mangment_system.user.UserManipulation;
import com.habbypanda.bus_mangment_system.user.parent.Parent;
import com.habbypanda.bus_mangment_system.user.parent.ParentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class StudentManipulation extends UserManipulation<Student> {
    private final AreaRepository areaRepository;
    private final ParentRepository parentRepository;
    public StudentManipulation(StudentRepository studentRepository, AreaRepository areaRepository, ParentRepository parentRepository) {
        super(studentRepository);
        this.areaRepository = areaRepository;
        this.parentRepository = parentRepository;
    }
    public Student updateStudentFromDTO(Student student, StudentDTO studentDTO) {
        return new Student(
                student.getId(),
                studentDTO.getName(),
                studentDTO.getEmail(),
                student.getPassword(),
                parentRepository.findById(studentDTO.getParentId()).orElse(null),
                areaRepository.findById(studentDTO.getAreaId()).orElse(null)
        );
    }
}

