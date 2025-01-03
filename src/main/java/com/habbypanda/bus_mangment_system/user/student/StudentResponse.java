package com.habbypanda.bus_mangment_system.user.student;

import com.habbypanda.bus_mangment_system.utils.DTOMapper;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class StudentResponse {
    private String message;
    private HttpStatus status;
    private StudentDTO student;
    private List<StudentDTO> students;

    public StudentResponse(String message, HttpStatus status, Student student) {
        this.message = message;
        this.status = status;
        this.student = DTOMapper.toStudentDTO(student);
    }
    public StudentResponse(String message, HttpStatus status, List<Student> students) {
        this.message = message;
        this.status = status;
        this.students = DTOMapper.toStudentDTOList(students);
    }
    public StudentResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
