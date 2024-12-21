package com.habbypanda.bus_mangment_system.area;

import com.habbypanda.bus_mangment_system.user.student.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dev/admin/area")
@RequiredArgsConstructor
public class AreaController {

    private final AreaService areaService;

    @PostMapping("/{areaId}/add-student/{studentId}")
    public ResponseEntity<String> addStudentToArea(@PathVariable Integer areaId, @PathVariable Integer studentId) {
        areaService.addStudentToArea(areaId, studentId);
        return ResponseEntity.ok("Student added to area successfully");
    }

    @GetMapping("/{areaId}/students")
    public ResponseEntity<List<Student>> getStudentsInArea(@PathVariable Integer areaId) {
        return ResponseEntity.ok(areaService.getStudentsInArea(areaId));
    }

    // Create a new area
    @PostMapping
    public ResponseEntity<Area> createArea(@RequestBody AreaRequest areaRequest) {
        Area area = areaService.createArea(areaRequest.getName());
        return ResponseEntity.ok(area);
    }

    // Get all areas
    @GetMapping
    public ResponseEntity<List<Area>> getAllAreas() {
        return ResponseEntity.ok(areaService.getAllAreas());
    }
}
