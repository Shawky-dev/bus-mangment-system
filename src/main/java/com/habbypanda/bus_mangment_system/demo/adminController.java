package com.habbypanda.bus_mangment_system.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/hi")
public class adminController {

    @GetMapping
    public String hello() {
        return "Hello from secure";
    }
}
