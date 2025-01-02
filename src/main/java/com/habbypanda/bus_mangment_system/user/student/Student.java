package com.habbypanda.bus_mangment_system.user.student;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.habbypanda.bus_mangment_system.area.Area;
import com.habbypanda.bus_mangment_system.route.Route;
import com.habbypanda.bus_mangment_system.stop.Stop;
import com.habbypanda.bus_mangment_system.user.parent.Parent;
import com.habbypanda.bus_mangment_system.user.Role;
import com.habbypanda.bus_mangment_system.user.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Student extends User {

    @OneToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;

    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    // Association with Route (Many students can have one route)
    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route; // Route associated with the student

    @ManyToOne
    @JoinColumn(name = "stop_id")
    private Stop stop;


    @Builder
    public Student(Integer id, String name, String email, String password, Parent parent) {
        super(id, name, email, password, Role.USER);
        this.parent = parent;
    }
}