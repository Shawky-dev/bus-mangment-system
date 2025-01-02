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
        return AreaResponse.builder().area(area).status(HttpStatus.CREATED).message("Area created successfully").build();
    }
    public AreaResponse getArea(Integer areaId) {
        if(!areaRepository.existsById(areaId)) {
            return AreaResponse.builder().status(HttpStatus.NOT_FOUND).message("Area not found").build();
        }
        Area area = areaRepository.findById(areaId).orElseThrow(() -> new RuntimeException("Area not found"));
        return AreaResponse.builder().area(area).status(HttpStatus.OK).message("Area fetched successfully").build();
    }
    public AreaResponse getAllAreas() {
        return AreaResponse.builder().areas(areaRepository.findAll()).status(HttpStatus.OK).message("Areas fetched successfully").build();
    }
    //Students
    public AreaResponse addStudentToArea(Integer areaId, Integer studentId) {
        if (!areaRepository.existsById(areaId)) {
            return AreaResponse.builder().status(HttpStatus.NOT_FOUND).message("Area not found").build();
        }
        Area area = areaRepository.findById(areaId).orElseThrow(() -> new RuntimeException("Area not found"));
        if (!studentRepository.existsById(studentId)) {
            return AreaResponse.builder().status(HttpStatus.NOT_FOUND).message("Student not found").build();
        }
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        area.getStudents().add(student);
        areaRepository.save(area);
        return AreaResponse.builder().area(area).status(HttpStatus.OK).message("Student added to area successfully").build();
    }
    public AreaResponse removeStudentFromArea(Integer areaId, Integer studentId) {
        if (!areaRepository.existsById(areaId)) {
            return AreaResponse.builder().status(HttpStatus.NOT_FOUND).message("Area not found").build();
        }
        Area area = areaRepository.findById(areaId).orElseThrow(() -> new RuntimeException("Area not found"));
        if (!studentRepository.existsById(studentId)) {
            return AreaResponse.builder().status(HttpStatus.NOT_FOUND).message("Student not found").build();
        }
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        area.getStudents().remove(student);
        areaRepository.save(area);
        return AreaResponse.builder().area(area).status(HttpStatus.OK).message("Student removed from area successfully").build();
    }
    //Routes
    public AreaResponse addRouteToArea(Integer areaId) {
        if (!areaRepository.existsById(areaId)) {
            return AreaResponse.builder().status(HttpStatus.NOT_FOUND).message("Area not found").build();
        }
        Area area = areaRepository.findById(areaId).orElseThrow(() -> new RuntimeException("Area not found"));
        Route route = Route.builder().area(area).build();
        area.getRoutes().add(route);
        areaRepository.save(area);
        routeRepository.save(route);
        return AreaResponse.builder().area(area).status(HttpStatus.OK).message("Route added to area successfully").build();
    }
    //Stops
    public AreaResponse addStopToArea(Integer areaId,String stopName) {
        if (!areaRepository.existsById(areaId)) {
            return AreaResponse.builder().status(HttpStatus.NOT_FOUND).message("Area not found").build();
        }
        Area area = areaRepository.findById(areaId).orElseThrow(() -> new RuntimeException("Area not found"));
        Stop stop = Stop.builder().name(stopName).area(area).build();
        area.getStops().add(stop);
        areaRepository.save(area);
        stopRepository.save(stop);
        return AreaResponse.builder().area(area).status(HttpStatus.OK).message("Stop added to area successfully").build();
    }
}
