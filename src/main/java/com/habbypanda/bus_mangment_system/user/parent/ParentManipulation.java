package com.habbypanda.bus_mangment_system.user.parent;

import com.habbypanda.bus_mangment_system.user.UserManipulation;
import com.habbypanda.bus_mangment_system.user.UserRepository;
import com.habbypanda.bus_mangment_system.user.student.Student;
import com.habbypanda.bus_mangment_system.user.student.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class ParentManipulation  extends UserManipulation<Parent> {
    public ParentManipulation(ParentRepository parentRepository) {
        super(parentRepository);
    }
}
