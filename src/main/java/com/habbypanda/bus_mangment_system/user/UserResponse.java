package com.habbypanda.bus_mangment_system.user;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class UserResponse<T extends User> {
    private String message;
    private HttpStatus status;
    private T user;
    private List<T> users;

    public UserResponse(String message, HttpStatus status, T user) {
        this.message = message;
        this.status = status;
        this.user = user;
    }

    public UserResponse(String message, HttpStatus status, List<T> users) {
        this.message = message;
        this.status = status;
        this.users = users;
    }

    public UserResponse(String message, HttpStatus httpStatus) {
        this.message = message;
        this.status = httpStatus;
    }
}