package com.habbypanda.bus_mangment_system.user.parent;

import com.habbypanda.bus_mangment_system.user.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParentRepository extends UserRepository<Parent,Integer>{
}