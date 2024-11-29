package com.habbypanda.bus_mangment_system.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticatiorResponse {
    private String jwt;
    private String message;
    private HttpStatus status;

}
