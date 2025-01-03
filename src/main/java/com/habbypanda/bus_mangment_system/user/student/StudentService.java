package com.habbypanda.bus_mangment_system.user.student;

import com.habbypanda.bus_mangment_system.area.Area;
import com.habbypanda.bus_mangment_system.area.AreaRepository;
import com.habbypanda.bus_mangment_system.route.Route;
import com.habbypanda.bus_mangment_system.route.RouteRepository;
import com.habbypanda.bus_mangment_system.stop.Stop;
import com.habbypanda.bus_mangment_system.stop.StopRepository;
import com.habbypanda.bus_mangment_system.utils.DTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StopRepository stopRepository;
    private final AreaRepository areaRepository;
    private final RouteRepository routeRepository;
    public StudentResponse getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return new StudentResponse("All students", HttpStatus.OK, students);
    }

    public StudentResponse selectStop(Integer studentId, Integer stopId) {
        if (!studentRepository.existsById(studentId)) {
            return new StudentResponse("Student not found", HttpStatus.NOT_FOUND);
        }
        if (!stopRepository.existsById(stopId)) {
            return new StudentResponse("Stop not found", HttpStatus.NOT_FOUND);
        }
        Stop stop = stopRepository.findById(stopId).orElseThrow(() -> new RuntimeException("Stop not found"));
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        student.setStop(stop);
        studentRepository.save(student);
        return new StudentResponse("Stop selected successfully", HttpStatus.OK, student);
    }

    public StudentResponse selectAreaStopRoute(Integer studentId, Integer areaId, Integer stopId, Integer routeId) {
        if (!studentRepository.existsById(studentId)) {
            return new StudentResponse("Student not found", HttpStatus.NOT_FOUND);
        }
        if (!areaRepository.existsById(areaId)) {
            return new StudentResponse("Area not found", HttpStatus.NOT_FOUND);
        }
        if (!stopRepository.existsByIdAndAreaId(stopId, areaId)) {
            return new StudentResponse("Stop not found in the specified area", HttpStatus.NOT_FOUND);
        }
        if (!routeRepository.existsByIdAndAreaId(routeId, areaId)) {
            return new StudentResponse("Route not found in the specified area", HttpStatus.NOT_FOUND);
        }
        Area area = areaRepository.findById(areaId).orElseThrow(() -> new RuntimeException("Area not found"));
        Stop stop = stopRepository.findById(stopId).orElseThrow(() -> new RuntimeException("Stop not found"));
        Route route = routeRepository.findById(routeId).orElseThrow(() -> new RuntimeException("Route not found"));
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        student.setArea(area);
        student.setStop(stop);
        student.setRoute(route);
        studentRepository.save(student);
        return new StudentResponse("Area, stop and route selected successfully", HttpStatus.OK, student);

    }
}