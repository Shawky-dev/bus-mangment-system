package com.habbypanda.bus_mangment_system.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//THIS CLASS IS NOT BEING USED ATM BECAUSE THERE CANT BE AN ACCOUNT WITH TYPE "USER" IN THE DATABASE
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);

}
