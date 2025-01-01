package com.habbypanda.bus_mangment_system.user.student;

import com.habbypanda.bus_mangment_system.user.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends UserRepository<Student,Integer>{
}