package com.habbypanda.bus_mangment_system.route;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.habbypanda.bus_mangment_system.area.Area;
import com.habbypanda.bus_mangment_system.route.TimeSlot.TimeSlot;
import com.habbypanda.bus_mangment_system.user.student.Student;
import com.habbypanda.bus_mangment_system.stop.Stop;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Represents a dynamic Route entity for managing student pick-up and drop-off schedules.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use auto-incremented integer ID
    private Integer id; // Unique identifier for the route

    @Column(nullable = false)
    private LocalDate date; // Specific date of the route

    @ManyToOne
    @JoinColumn(name = "time_slot_id", nullable = false)
    private TimeSlot timeSlot; // Time slot for the route (e.g., 8:30 AM)

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RouteType type; // Type of route: PICKUP or DROPOFF

    //Route Status
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RouteStatus status;

    // Association with an Area (Many routes can be associated with one area)
    @ManyToOne
    @JoinColumn(name = "area_id", nullable = false)
    @JsonBackReference
    private Area area; // Association with an Area

    // Association with Student (One route can have many students)
    @OneToMany(mappedBy = "route")
    private List<Student> students; // Students registered for this route

    // Association with Stops (Many routes can have many stops)
    @ManyToMany
    @JoinTable(
            name = "route_stops",
            joinColumns = @JoinColumn(name = "route_id"),
            inverseJoinColumns = @JoinColumn(name = "stop_id")
    )
    private List<Stop> stops; // Stops for the route

    @Builder
    public Route(LocalDate date, TimeSlot timeSlot, RouteType type, Area area,RouteStatus status) {
        this.date = date;
        this.timeSlot = timeSlot;
        this.type = type;
        this.area = area;
        this.status = status;
    }
}
