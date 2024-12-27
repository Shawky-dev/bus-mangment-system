package com.habbypanda.bus_mangment_system.auth;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.*;

//Data is an annotation that automatically generates getters and setters for all fields in the class
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    private String email;
    private String password;
}
