package com.habbypanda.bus_mangment_system.area;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AreaResponse {
    private String message;
    private HttpStatus status;
    private Area area;
    private List<Area> areas;
}
