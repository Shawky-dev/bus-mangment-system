package com.habbypanda.bus_mangment_system.dropOff_pickUp;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing DropOffPickUp locations.
 */
@RestController
@RequestMapping("/dev/admin/dropOffPickUp")
@RequiredArgsConstructor
public class DropOffPickUpController {

    private final DropOffPickUpService dropOffPickUpService;

    @PostMapping("/create")
    public ResponseEntity<DropOffPickUpResponse> createLocation(@RequestBody DropOffPickUpRequest request) {
        DropOffPickUpResponse response = dropOffPickUpService.createLocation(
                request.getAreaId(),
                request.getLocationName()
        );
        return ResponseEntity.status(response.getStatus()).body(response);
    }



    // Get all DropOffPickUp locations for a specific area
    @GetMapping("/getArea/{areaId}")
    public ResponseEntity<DropOffPickUpResponse> getLocationsByArea(@PathVariable Integer areaId) {
        DropOffPickUpResponse response = dropOffPickUpService.getLocationsByArea(areaId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // Delete a DropOffPickUp location
    @DeleteMapping("/deleteLocation/{areaId}/{locationId}")
    public ResponseEntity<DropOffPickUpResponse> deleteLocation(
            @PathVariable Integer areaId,
            @PathVariable Integer locationId) {
        DropOffPickUpResponse response = dropOffPickUpService.deleteLocation(areaId, locationId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
