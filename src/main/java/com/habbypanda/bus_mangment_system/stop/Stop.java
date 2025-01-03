package com.habbypanda.bus_mangment_system.stop;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.habbypanda.bus_mangment_system.area.Area;
import com.habbypanda.bus_mangment_system.route.Route;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "stops")
@AllArgsConstructor
@Builder
public class Stop {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private Integer priority;
    @ManyToOne
    @JoinColumn(name = "area_id", nullable = false)
    private Area area;



}
