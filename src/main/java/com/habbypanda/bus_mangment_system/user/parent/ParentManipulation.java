package com.habbypanda.bus_mangment_system.user.parent;

import com.habbypanda.bus_mangment_system.user.UserManipulation;
import com.habbypanda.bus_mangment_system.user.student.Student;
import com.habbypanda.bus_mangment_system.user.student.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ParentManipulation extends UserManipulation<Parent> {
    private final StudentRepository studentRepository;
    public ParentManipulation(ParentRepository parentRepository, StudentRepository studentRepository) {
        super(parentRepository);
        this.studentRepository = studentRepository;
    }

    public Parent updateParentFromDTO(Parent parent, ParentDTO parentDTO) {
        Student student = (parentDTO.getStudentId() != null) ? studentRepository.findById(parentDTO.getStudentId()).orElse(null) : null;
        Parent newParent = Parent.builder()
                .id(parent.getId())
                .name(parentDTO.getName())
                .email(parentDTO.getEmail())
                .password(parent.getPassword())
                .build();
        log.info("Parent updated successfully");
        return newParent;
    }
}