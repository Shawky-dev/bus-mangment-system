package com.habbypanda.bus_mangment_system.auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthenticatorResponse {
    private String jwt;
    private String message;
    private HttpStatus status;

    public void setHttpOnlyCookie(HttpServletResponse response) {
        if (jwt != null) {
            Cookie cookie = new Cookie("jwt", jwt);
            cookie.setHttpOnly(true);
            cookie.setSecure(true); // Set to true if using HTTPS
            cookie.setPath("/");
            cookie.setMaxAge(86400); // 1 day in seconds
            response.addCookie(cookie);
        }else{
            setStatus(HttpStatus.UNAUTHORIZED);
        }
    }
    public void clearHttpOnlyCookie(HttpServletResponse response) {
            Cookie cookie = new Cookie("jwt", null);
            cookie.setHttpOnly(true);
            cookie.setSecure(true); // Set to true if using HTTPS
            cookie.setPath("/");
            cookie.setMaxAge(0); // 1 day in seconds
            response.addCookie(cookie);

            setStatus(HttpStatus.OK);
    }
}