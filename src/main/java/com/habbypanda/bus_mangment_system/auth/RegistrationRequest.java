package com.habbypanda.bus_mangment_system.auth;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
     private String name;
     private String email;
     private String password;
}
