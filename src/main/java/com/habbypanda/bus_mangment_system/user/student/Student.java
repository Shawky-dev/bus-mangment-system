package com.habbypanda.bus_mangment_system.user.student;

import com.habbypanda.bus_mangment_system.area.Area;
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
public class Student extends User {

    @OneToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;
    @ManyToOne
    @JoinColumn(name = "area_id")
    private Area area;

    @ManyToOne
    @JoinColumn(name = "stop_id")
    private Stop stop;

    @Builder
    public Student(Integer id, String name, String email, String password, Parent parent, Area area, Stop stop) {
        super(id, name, email, password, Role.USER);
        this.parent = parent;
        this.area = area;
        this.stop = stop;
    }


}
