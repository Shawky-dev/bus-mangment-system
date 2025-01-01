package com.habbypanda.bus_mangment_system.stop;

import com.habbypanda.bus_mangment_system.area.Area;
import lombok.*;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class StopDTO {
    private Integer id;
    private String locationName;
    private Integer areaID;

    public StopDTO(Integer id, String locationName, Area area) {
        this.id = id;
        this.locationName = locationName;
        this.areaID = area.getId();
    }

    public StopDTO(Stop stop) {
        this.id = stop.getId();
        this.locationName = stop.getLocationName();
        this.areaID = Optional.ofNullable(stop.getArea())
                .map(Area::getId)
                .orElse(null);
    }
}