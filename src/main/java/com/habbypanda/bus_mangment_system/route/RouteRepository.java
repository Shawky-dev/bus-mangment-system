package com.habbypanda.bus_mangment_system.route;

import com.habbypanda.bus_mangment_system.route.TimeSlot.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

/**
 * Repository interface for Route entity.
 */
public interface RouteRepository extends JpaRepository<Route, Integer> {

    // Find all routes by area ID
    List<Route> findByArea_Id(Integer areaId);

    // Find routes by area ID and specific time slot
    List<Route> findByArea_IdAndTimeSlot(Integer areaId, TimeSlot timeSlot);

    // Find routes by date and area
    List<Route> findByDateAndArea_Id(LocalDate date, Integer areaId);
}
