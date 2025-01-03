package com.habbypanda.bus_mangment_system.user.parent;

import com.habbypanda.bus_mangment_system.user.student.Student;
import com.habbypanda.bus_mangment_system.user.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParentService {
    private final ParentRepository parentRepository;
    private final StudentRepository studentRepository;
    public ParentResponse getAllParents() {
        return new ParentResponse("All parents", HttpStatus.OK,parentRepository.findAll());
    }

    public ParentResponse addStudentToParent(Integer parentId, Integer studentId) {
        Parent parent = parentRepository.findById(parentId).orElse(null);
        if (parent == null) {
            return new ParentResponse("Parent not found", HttpStatus.NOT_FOUND);
        }
        Student student = studentRepository.findById(studentId).orElse(null);
        if (student == null) {
            return new ParentResponse("Student not found", HttpStatus.NOT_FOUND);
        }
        parent.setStudent(student);
        student.setParent(parent);
        parentRepository.save(parent);
        studentRepository.save(student);

        return new ParentResponse("Student added to parent", HttpStatus.OK, parent);

    }
}
