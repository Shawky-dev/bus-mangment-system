package com.habbypanda.bus_mangment_system.stop;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing DropOffPickUp locations.
 */
@RestController
@RequestMapping("/dev/admin/dropOffPickUp")
@RequiredArgsConstructor
public class StopController {

    private final StopService stopService;

    @PostMapping("/create")
    public ResponseEntity<StopResponse> createLocation(@RequestBody StopRequest request) {
        StopResponse response = stopService.createLocation(
                request.getAreaId(),
                request.getLocationName()
        );
        return ResponseEntity.status(response.getStatus()).body(response);
    }



    // Get all DropOffPickUp locations for a specific area
    @GetMapping("/getArea/{areaId}")
    public ResponseEntity<StopResponse> getLocationsByArea(@PathVariable Integer areaId) {
        StopResponse response = stopService.getLocationsByArea(areaId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // Delete a DropOffPickUp location
    @DeleteMapping("/deleteLocation/{areaId}/{locationId}")
    public ResponseEntity<StopResponse> deleteLocation(
            @PathVariable Integer areaId,
            @PathVariable Integer locationId) {
        StopResponse response = stopService.deleteLocation(areaId, locationId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
