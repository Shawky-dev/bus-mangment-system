package com.habbypanda.bus_mangment_system.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository<T extends User, ID> extends JpaRepository<T, ID> {
    Optional<T> findByEmail(String email);
}
