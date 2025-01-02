package com.habbypanda.bus_mangment_system.area;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.habbypanda.bus_mangment_system.user.student.Student;
import com.habbypanda.bus_mangment_system.route.Route;
import com.habbypanda.bus_mangment_system.stop.Stop;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "areas")
public class Area {

    @Id
    @GeneratedValue
    private Integer id; // Unique identifier for the area

    @Column(nullable = false, unique = true)
    private String name; // Name of the area (e.g., Tagmo3, Sheikh Zayed)

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "area_id") // Creates an area_id column in the Student table
    private List<Student> students = new ArrayList<>(); // Students linked to this area

    @OneToMany(mappedBy = "area", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Route> routes; // Routes generated for this area

    @OneToMany(mappedBy = "area", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Stop> stops; // Static list of Stops

    @Builder
    public Area(String name) {
        this.name = name;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }
}
