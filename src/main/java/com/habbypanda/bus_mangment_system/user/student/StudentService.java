package com.habbypanda.bus_mangment_system.user.student;

import com.habbypanda.bus_mangment_system.stop.Stop;
import com.habbypanda.bus_mangment_system.stop.StopRepository;
import com.habbypanda.bus_mangment_system.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StopRepository stopRepository;
    public UserResponse<Student> getAllStudents() {
        return new UserResponse<Student>("All students", HttpStatus.OK,studentRepository.findAll());
    }
}
