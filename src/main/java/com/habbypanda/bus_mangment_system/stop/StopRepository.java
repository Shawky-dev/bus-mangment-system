package com.habbypanda.bus_mangment_system.stop;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for DropOffPickUp entity.
 */
public interface StopRepository extends JpaRepository<Stop, Integer> {

    // Find all stops by area ID
    List<Stop> findByAreaId(Integer areaId);
}
