package com.habbypanda.bus_mangment_system.bus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bus")
public class BusController {

    @Autowired
    private BusService busService;

    @PostMapping("/create")
    public ResponseEntity<BusResponse> createBus(@RequestBody BusCreateRequest request) {
        return ResponseEntity.ok(busService.createBus(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusResponse> getBusById(@PathVariable Integer id) {
        return ResponseEntity.ok(busService.getBusById(id));
    }

    @GetMapping
    public ResponseEntity<List<BusResponse>> getAllBuses() {
        return ResponseEntity.ok(busService.getAllBuses());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BusResponse> deleteBus(@PathVariable Integer id) {
        return ResponseEntity.ok(busService.deleteBus(id));
    }
}
