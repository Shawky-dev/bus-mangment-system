package com.habbypanda.bus_mangment_system.dropOff_pickUp;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dropoff_pickup")
public class DropOffPickUp {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id; // Unique identifier for the location

    @Column(nullable = false)
    private String locationName; // Descriptive name of the location (e.g., Street 1, Gate 3)

    @Builder
    public DropOffPickUp(String locationName) {
        this.locationName = locationName;
    }
}
