package com.habbypanda.bus_mangment_system.user.parent;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/parent")
@RequiredArgsConstructor
public class ParentController {
    private final ParentService parentService;
    @GetMapping("/getAllParents")
    public ResponseEntity<ParentResponse> getAllParents() {
        ParentResponse response = parentService.getAllParents();
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @PutMapping("/addStudentToParent")
    public ResponseEntity<ParentResponse> addStudentToParent(@RequestParam Integer parentId, @RequestParam Integer studentId) {
        ParentResponse response = parentService.addStudentToParent(parentId, studentId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
