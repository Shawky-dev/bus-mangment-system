package com.habbypanda.bus_mangment_system.bus;

import lombok.Data;

@Data
public class BusCreateRequest {
    private String plateNumber;
    private Integer capacity;
    private Integer routeId;
    private Integer driverId;
}
