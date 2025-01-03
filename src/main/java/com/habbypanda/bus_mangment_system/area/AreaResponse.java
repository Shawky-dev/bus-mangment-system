package com.habbypanda.bus_mangment_system.area;

import com.habbypanda.bus_mangment_system.utils.DTOMapper;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AreaResponse
{
    private String message;
    private HttpStatus status;
    private AreaDTO area; // Single AreaDTO
    private List<AreaDTO> areas; // List of AreaDTOs

    // Constructor for single Area
    public AreaResponse(String message, HttpStatus status, Area area) {
        this.message = message;
        this.status = status;
        this.area = DTOMapper.toAreaDTO(area); // Convert Area to AreaDTO
    }

    // Constructor for list of Areas
    public AreaResponse(String message, HttpStatus status, List<Area> areas) {
        this.message = message;
        this.status = status;
        this.areas = DTOMapper.toAreaDTOList(areas); // Convert List<Area> to List<AreaDTO>
    }
}