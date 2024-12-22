package com.habbypanda.bus_mangment_system.route;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class DropOffPickUpController {

    private final DropOffPickUpService dropOffPickUpService;

    @PostMapping
    public ResponseEntity<DropOffPickUp> createLocation(@RequestBody String locationName) {
        DropOffPickUp location = dropOffPickUpService.createLocation(locationName);
        return ResponseEntity.ok(location);
    }

    @GetMapping
    public ResponseEntity<List<DropOffPickUp>> getAllLocations() {
        return ResponseEntity.ok(dropOffPickUpService.getAllLocations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DropOffPickUp> getLocationById(@PathVariable UUID id) {
        return ResponseEntity.ok(dropOffPickUpService.getLocationById(id));
    }
}
