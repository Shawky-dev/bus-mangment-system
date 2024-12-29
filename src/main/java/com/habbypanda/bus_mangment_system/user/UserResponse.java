package com.habbypanda.bus_mangment_system.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse<T> {
    private String message;
    private HttpStatus status;
    private List<T> users;
}
