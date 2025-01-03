package com.habbypanda.bus_mangment_system.stop;

import com.habbypanda.bus_mangment_system.area.AreaDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StopDTO {
    private Integer id;
    private String name;

    private Integer priority;
    private Integer AreaId;
}