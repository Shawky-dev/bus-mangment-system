package com.habbypanda.bus_mangment_system.area;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentAreaRequest
{
    private Integer areaId;
    private Integer studentId;

}
