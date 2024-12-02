package com.habbypanda.bus_mangment_system.auth;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParentRegistrationRequest{
     private String name;
     private String email;
     private String password;
}
