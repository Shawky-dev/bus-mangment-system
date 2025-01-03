package com.habbypanda.bus_mangment_system.stop;

import com.habbypanda.bus_mangment_system.route.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StopRepository extends JpaRepository<Stop,Integer> {


    boolean existsByIdAndAreaId(Integer stopId, Integer areaId);
}
