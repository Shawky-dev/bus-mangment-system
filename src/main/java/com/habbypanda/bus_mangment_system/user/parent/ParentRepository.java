package com.habbypanda.bus_mangment_system.user.parent;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParentRepository extends JpaRepository<Parent,Integer> {
    Optional<Parent> findByEmail(String email);

}