package com.habbypanda.bus_mangment_system.dropOff_pickUp;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DropOffPickUpRepository extends JpaRepository<DropOffPickUp, UUID> {
}
