package com.habbypanda.bus_mangment_system.area;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AreaRequest {
    private String name; // Name of the area (e.g., Tagmo3, Sheikh Zayed)
}
