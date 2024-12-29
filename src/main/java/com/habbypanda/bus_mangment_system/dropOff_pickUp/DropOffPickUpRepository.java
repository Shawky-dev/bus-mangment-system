package com.habbypanda.bus_mangment_system.dropOff_pickUp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository interface for DropOffPickUp entity.
 */
public interface DropOffPickUpRepository extends JpaRepository<DropOffPickUp, Integer> {

    // Find all locations by area ID
    List<DropOffPickUp> findByAreaId(Integer areaId);
}
