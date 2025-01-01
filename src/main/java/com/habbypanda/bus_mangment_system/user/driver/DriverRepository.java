package com.habbypanda.bus_mangment_system.user.driver;


import com.habbypanda.bus_mangment_system.user.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DriverRepository extends UserRepository<Driver,Integer> {

}