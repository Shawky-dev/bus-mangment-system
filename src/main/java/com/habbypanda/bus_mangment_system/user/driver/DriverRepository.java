package com.habbypanda.bus_mangment_system.user.driver;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver,Integer> {
    Optional<Driver> findByEmail(String email);


}