package com.habbypanda.bus_mangment_system.user.student;

import com.habbypanda.bus_mangment_system.area.Area;
import com.habbypanda.bus_mangment_system.route.DropOffPickUp;
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
@Table(name = "Student")
public class Student extends User {

    @OneToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private DropOffPickUp location; // Shared location for both pick-up and drop-off

    @Builder
    public Student(Integer id, String name, String email, String password, Parent parent, Area area, DropOffPickUp location) {
        super(id, name, email, password, Role.USER);
        this.parent = parent;
        this.location = location;
    }
}
