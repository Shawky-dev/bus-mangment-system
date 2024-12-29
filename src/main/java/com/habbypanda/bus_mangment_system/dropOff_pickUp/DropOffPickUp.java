package com.habbypanda.bus_mangment_system.dropOff_pickUp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.habbypanda.bus_mangment_system.area.Area;
import jakarta.persistence.*;
import lombok.*;

/**
 * Represents a static Drop-Off/Pick-Up location linked to Areas.
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
    @JsonBackReference
    private Area area; // Each location belongs to one area

    @Builder
    public DropOffPickUp(String locationName, Area area) {
        this.locationName = locationName;
        this.area = area;
    }
}
