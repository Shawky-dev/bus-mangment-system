package com.habbypanda.bus_mangment_system.area;

import com.habbypanda.bus_mangment_system.route.Route;
import com.habbypanda.bus_mangment_system.route.RouteRepository;
import com.habbypanda.bus_mangment_system.stop.Stop;
import com.habbypanda.bus_mangment_system.stop.StopRepository;
import com.habbypanda.bus_mangment_system.user.student.Student;
import com.habbypanda.bus_mangment_system.user.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AreaService {
    private final AreaRepository areaRepository;
    private final StudentRepository studentRepository;
    private final StopRepository stopRepository;
    private final RouteRepository routeRepository;

    public AreaResponse createArea(String name) {
        Area area = Area.builder().name(name).build();
        areaRepository.save(area);
        return new AreaResponse("Area created successfully", HttpStatus.CREATED, area);
    }

    public AreaResponse getArea(Integer areaId) {
        if (!areaRepository.existsById(areaId)) {
            return new AreaResponse("Area not found", HttpStatus.NOT_FOUND, (Area) null);
        }
        Area area = areaRepository.findById(areaId).orElseThrow(() -> new RuntimeException("Area not found"));
        return new AreaResponse("Area fetched successfully", HttpStatus.OK, area);
    }

    public AreaResponse getAllAreas() {
        List<Area> areas = areaRepository.findAll();
        return new AreaResponse("Areas fetched successfully", HttpStatus.OK, areas);
    }

    // Students
    public AreaResponse addStudentToArea(Integer areaId, Integer studentId) {
        if (!areaRepository.existsById(areaId)) {
            return new AreaResponse("Area not found", HttpStatus.NOT_FOUND, (Area) null);
        }
        Area area = areaRepository.findById(areaId).orElseThrow(() -> new RuntimeException("Area not found"));
        if (!studentRepository.existsById(studentId)) {
            return new AreaResponse("Student not found", HttpStatus.NOT_FOUND, (Area) null);
        }
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        area.getStudents().add(student);
        areaRepository.save(area);
        return new AreaResponse("Student added to area successfully", HttpStatus.OK, area);
    }

    public AreaResponse removeStudentFromArea(Integer areaId, Integer studentId) {
        if (!areaRepository.existsById(areaId)) {
            return new AreaResponse("Area not found", HttpStatus.NOT_FOUND, (Area) null);
        }
        Area area = areaRepository.findById(areaId).orElseThrow(() -> new RuntimeException("Area not found"));
        if (!studentRepository.existsById(studentId)) {
            return new AreaResponse("Student not found", HttpStatus.NOT_FOUND, (Area) null);
        }
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        area.getStudents().remove(student);
        areaRepository.save(area);
        return new AreaResponse("Student removed from area successfully", HttpStatus.OK, area);
    }

    // Stops
    public AreaResponse addStopToArea(Integer areaId, String stopName) {
        if (!areaRepository.existsById(areaId)) {
            return new AreaResponse("Area not found", HttpStatus.NOT_FOUND, (Area) null);
        }
        Area area = areaRepository.findById(areaId).orElseThrow(() -> new RuntimeException("Area not found"));
        Stop stop = Stop.builder().name(stopName).area(area).build();
        area.getStops().add(stop);
        areaRepository.save(area);
        stopRepository.save(stop);
        return new AreaResponse("Stop added to area successfully", HttpStatus.OK, area);
    }
}