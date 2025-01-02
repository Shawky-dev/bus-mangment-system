package com.habbypanda.bus_mangment_system.area;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/area")
@RequiredArgsConstructor
public class AreaController {
    private final AreaService areaService;

    @PostMapping("/createArea")
    public ResponseEntity<AreaResponse> createArea(@RequestBody AreaRequest areaRequest) {
        AreaResponse response = areaService.createArea(areaRequest.getName());
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @GetMapping("/getAllAreas")
    public ResponseEntity<AreaResponse> getAllAreas() {
        AreaResponse response = areaService.getAllAreas();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @GetMapping("/getArea")
    public ResponseEntity<AreaResponse> getArea(@RequestParam Integer areaId) {
        AreaResponse response = areaService.getArea(areaId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    ///Students
    @PutMapping("/addStudentToArea")
    public ResponseEntity<AreaResponse> addStudentToArea(@RequestParam Integer areaId, @RequestParam Integer studentId) {
        AreaResponse response = areaService.addStudentToArea(areaId, studentId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @PutMapping("/removeStudentFromArea")
    public ResponseEntity<AreaResponse> removeStudentFromArea(@RequestParam Integer areaId, @RequestParam Integer studentId) {
        AreaResponse response = areaService.removeStudentFromArea(areaId, studentId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    ///Routes
    @PostMapping("/addRouteToArea")
    public ResponseEntity<AreaResponse> addRouteToArea(@RequestParam Integer areaId) {
        AreaResponse response = areaService.addRouteToArea(areaId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @PostMapping("/addStopToArea")
    public ResponseEntity<AreaResponse> addStopToRoute(@RequestParam Integer areaId, @RequestParam String stopName) {
        AreaResponse response = areaService.addStopToArea(areaId, stopName);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
