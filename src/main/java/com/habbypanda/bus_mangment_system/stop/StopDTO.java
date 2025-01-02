package com.habbypanda.bus_mangment_system.stop;

import com.habbypanda.bus_mangment_system.area.Area;
import lombok.*;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class StopDTO {
    private Integer id;
    private String stopName;
    private Integer areaID;

    public StopDTO(Integer id, String stopName, Area area) {
        this.id = id;
        this.stopName = StopDTO.this.stopName;
        this.areaID = area.getId();
    }

    public StopDTO(Stop stop) {
        this.id = stop.getId();
        this.stopName = stop.getStopName();
        this.areaID = Optional.ofNullable(stop.getArea())
                .map(Area::getId)
                .orElse(null);
    }
}