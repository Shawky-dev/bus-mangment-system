package com.habbypanda.bus_mangment_system.user.parent;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/parent")
@RequiredArgsConstructor
public class ParentController {
    private final ParentService parentService;
    @GetMapping("getAllParents")
    public ParentResponse getAllParents() {
        return parentService.getAllParents();
    }
    @PutMapping("addStudentToParent")
    public ParentResponse addStudentToParent(@RequestParam Integer parentId, @RequestParam Integer studentId) {
        return parentService.addStudentToParent(parentId, studentId);
    }
}
