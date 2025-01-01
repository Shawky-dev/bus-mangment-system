package com.habbypanda.bus_mangment_system.auth;

import com.habbypanda.bus_mangment_system.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtCheckResponse {
    private Role role;
    private String email;
    private String message;
    private HttpStatus status;
}
