package com.habbypanda.bus_mangment_system.dropOff_pickUp;


import com.habbypanda.bus_mangment_system.area.Area;
import com.habbypanda.bus_mangment_system.route.Route;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Represents a static Drop-Off/Pick-Up location linked to Areas and Routes.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dropoff_pickup")
public class DropOffPickUp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremented ID
    private Integer id;

    @Column(nullable = false)
    private String locationName; // Descriptive name of the location (e.g., Gate 1, Street 5)

    @ManyToOne
    @JoinColumn(name = "area_id", nullable = false)
    private Area area; // Each location belongs to one area

    @ManyToMany(mappedBy = "locations")
    private List<Route> routes; // Locations can dynamically belong to multiple routes

    @Builder
    public DropOffPickUp(String locationName, Area area) {
        this.locationName = locationName;
        this.area = area;
    }
}
