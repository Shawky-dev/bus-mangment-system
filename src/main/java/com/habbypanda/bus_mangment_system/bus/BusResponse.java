package com.habbypanda.bus_mangment_system.bus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusResponse {
    private String message;
    private HttpStatus status;
    private Bus bus;
}
