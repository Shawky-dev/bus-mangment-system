package com.habbypanda.bus_mangment_system.user.student;

import com.habbypanda.bus_mangment_system.area.Area;
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
    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;


    @Builder
    public Student(Integer id, String name, String email, String password, Parent parent, Area area) {
        super(id, name, email, password, Role.USER);
        this.parent = parent;
        this.area = area;
    }
 
}
