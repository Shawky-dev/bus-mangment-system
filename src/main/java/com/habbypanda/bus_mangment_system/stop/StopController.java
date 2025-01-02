package com.habbypanda.bus_mangment_system.stop;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing DropOffPickUp locations.
 */
@RestController
@RequestMapping("/dev/admin/stop")
@RequiredArgsConstructor
public class StopController {

    private final StopService stopService;

    @PostMapping("/create")
    public ResponseEntity<StopResponse> createStop(@RequestBody StopRequest request) {
        StopResponse response = stopService.createStop(
                request.getAreaId(),
                request.getStopName()
        );
        return ResponseEntity.status(response.getStatus()).body(response);
    }



    // Get all DropOffPickUp locations for a specific area
    @GetMapping("/getArea/{areaId}")
    public ResponseEntity<StopResponse> getStopByArea(@PathVariable Integer areaId) {
        StopResponse response = stopService.getStopsByArea(areaId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    // Delete a DropOffPickUp location
    @DeleteMapping("/deleteStop/{areaId}/{stopId}")
    public ResponseEntity<StopResponse> deleteStop(
            @PathVariable Integer areaId,
            @PathVariable Integer stopId) {
        StopResponse response = stopService.deleteStop(areaId, stopId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
