package com.habbypanda.bus_mangment_system.area;

import com.habbypanda.bus_mangment_system.user.student.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dev/admin/area")
@RequiredArgsConstructor
public class AreaController {

    private final AreaService areaService;

    // Edit Area
    @PutMapping("/{areaId}/edit")
    public ResponseEntity<AreaResponse> editArea(@PathVariable Integer areaId, @RequestBody AreaRequest areaRequest) {
        AreaResponse response = areaService.editArea(areaId, areaRequest.getName());
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // Delete Area
    @DeleteMapping("/{areaId}/delete")
    public ResponseEntity<AreaResponse> deleteArea(@PathVariable Integer areaId) {
        AreaResponse response = areaService.deleteArea(areaId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // Remove Student from Area
    @DeleteMapping("/{areaId}/removeStudent/{studentId}")
    public ResponseEntity<AreaResponse> removeStudentFromArea(@PathVariable Integer areaId, @PathVariable Integer studentId) {
        AreaResponse response = areaService.removeStudentFromArea(areaId, studentId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }


    // Add a student to an area
    @PostMapping("/addStudentToArea")
    public ResponseEntity<AreaResponse> addStudentToArea(@RequestBody StudentAreaRequest request) {
        AreaResponse response = areaService.addStudentToArea(request.getAreaId(), request.getStudentId());
        return ResponseEntity.status(response.getStatus()).body(response);
    }


    // Get all students in an area
    @GetMapping("/{areaId}/students")
    public ResponseEntity<AreaResponse> getStudentsInArea(@PathVariable Integer areaId) {
        AreaResponse response = areaService.getStudentsInArea(areaId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }


    // Create a new area
    @PostMapping("/createArea")
    public ResponseEntity<AreaResponse> createArea(@RequestBody AreaRequest areaRequest) {
        AreaResponse response = areaService.createArea(areaRequest.getName());
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // Get all areas
    @GetMapping
    public ResponseEntity<List<Area>> getAllAreas() {
        return ResponseEntity.ok(areaService.getAllAreas());
    }
}
