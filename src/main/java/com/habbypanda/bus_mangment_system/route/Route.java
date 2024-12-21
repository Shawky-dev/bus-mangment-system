package com.habbypanda.bus_mangment_system.route;

import com.habbypanda.bus_mangment_system.area.Area;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id; // Unique identifier for the route

    @Column(nullable = false)
    private String timeSlot; // Time slot for the route (e.g., Morning, Afternoon)

    @ManyToOne
    @JoinColumn(name = "area_id", nullable = false)
    private Area area; // Association with an Area

    @Builder
    public Route(String timeSlot, Area area) {
        this.timeSlot = timeSlot;
        this.area = area;
    }
}
