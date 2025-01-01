package com.habbypanda.bus_mangment_system.user.admin;

import com.habbypanda.bus_mangment_system.user.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends UserRepository<Admin,Integer> {



}
