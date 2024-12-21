package com.habbypanda.bus_mangment_system.route;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface DropOffPickUpRepository extends JpaRepository<DropOffPickUp, UUID> {
}
