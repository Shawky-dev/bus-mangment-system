package com.habbypanda.bus_mangment_system.user.parent;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.habbypanda.bus_mangment_system.user.Role;
import com.habbypanda.bus_mangment_system.user.User;
import com.habbypanda.bus_mangment_system.user.student.Student;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "parents")
@NoArgsConstructor
public class Parent extends User {

    @OneToOne(mappedBy = "parent", cascade = CascadeType.ALL)
    private Student student;
    @Builder
    public Parent(Integer id, String name, String email, String password, Student student) {
        super(id, name, email, password, Role.USER);
        this.student = student;

    }
}
