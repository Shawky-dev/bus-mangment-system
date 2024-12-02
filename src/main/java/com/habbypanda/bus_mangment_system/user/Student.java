package com.habbypanda.bus_mangment_system.user;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // Required by Hibernate
@Entity
@Table(name = "Student")
public class Student extends User {

    @OneToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;

    // Parameterized constructor for Builder
    @Builder
    public Student(Integer id, String name, String email, String password, Role role, Parent parent) {
        super(id, name, email, password, role); // Calls the superclass constructor
        this.parent = parent;
    }
}
