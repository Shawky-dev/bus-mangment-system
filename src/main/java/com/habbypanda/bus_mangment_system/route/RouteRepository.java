package com.habbypanda.bus_mangment_system.route;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Repository interface for Route entity.
 */
public interface RouteRepository extends JpaRepository<Route, Integer> {

    // Find all routes by area ID
    List<Route> findByAreaId(Integer areaId);

    // Find routes by area ID and specific time slot
    List<Route> findByAreaIdAndTimeSlot(Integer areaId, LocalTime timeSlot);

    // Find routes by date and area
    List<Route> findByDateAndAreaId(LocalDate date, Integer areaId);
}
