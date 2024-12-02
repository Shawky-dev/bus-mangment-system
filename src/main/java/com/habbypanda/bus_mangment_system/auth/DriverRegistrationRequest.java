package com.habbypanda.bus_mangment_system.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DriverRegistrationRequest {
     private String name;
     private String email;
     private String password;
}
