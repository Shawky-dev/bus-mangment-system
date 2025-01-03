package com.habbypanda.bus_mangment_system.user.parent;

import com.habbypanda.bus_mangment_system.user.Role;
import com.habbypanda.bus_mangment_system.user.student.StudentDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParentDTO {
    private Integer id; // Unique identifier for the parent
    private String name; // Name of the parent
    private String email; // Email of the parent
    private Role role; // Role of the parent
    private Integer studentId; // Associated student (as a DTO)
}