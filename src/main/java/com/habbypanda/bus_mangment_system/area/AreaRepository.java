package com.habbypanda.bus_mangment_system.area;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AreaRepository extends JpaRepository<Area, Integer>
{
    Optional<Area> findByName(String name);
}
