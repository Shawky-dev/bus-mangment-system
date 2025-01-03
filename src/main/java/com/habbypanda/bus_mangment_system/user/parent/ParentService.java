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
        Student newStudent = studentRepository.findById(studentId).orElse(null);
        if (newStudent == null) {
            return new ParentResponse("Student not found", HttpStatus.NOT_FOUND);
        }
        // If parent already has a student, disassociate the old student
        if (parent.getStudent() != null) {
            Student oldStudent = parent.getStudent();
            oldStudent.setParent(null);
            studentRepository.save(oldStudent);
        }
        // If new student already has a parent, disassociate the old parent
        if (newStudent.getParent() != null && !newStudent.getParent().getId().equals(parentId)) {
            Parent oldParent = newStudent.getParent();
            oldParent.setStudent(null);
            parentRepository.save(oldParent);
        }
        // Assign new student to parent
        newStudent.setParent(parent);
        parent.setStudent(newStudent);
        // Save both
        studentRepository.save(newStudent);
        parentRepository.save(parent);
        return new ParentResponse("Student added to parent", HttpStatus.OK, parent);
    }
}
