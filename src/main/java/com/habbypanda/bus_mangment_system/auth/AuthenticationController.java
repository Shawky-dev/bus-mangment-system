package com.habbypanda.bus_mangment_system.auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/registerStudent")
    public ResponseEntity<AuthenticatorResponse> registerStudent(@RequestBody StudentRegistrationRequest request, HttpServletResponse response) {
        AuthenticatorResponse authResponse = authenticationService.registerStudent(request);
        authResponse.setHttpOnlyCookie(response);
        return ResponseEntity.status(authResponse.getStatus()).body(authResponse);
    }
    @PostMapping("/registerParent")
    public ResponseEntity<AuthenticatorResponse> registerParent(@RequestBody ParentRegistrationRequest request, HttpServletResponse response) {
        AuthenticatorResponse authResponse = authenticationService.registerParent(request);
        authResponse.setHttpOnlyCookie(response);
        return ResponseEntity.status(authResponse.getStatus()).body(authResponse);
    }

    @PostMapping("/registerDriver")
    public ResponseEntity<AuthenticatorResponse> registerDriver(@RequestBody DriverRegistrationRequest request, HttpServletResponse response) {
        AuthenticatorResponse authResponse = authenticationService.registerDriver(request);
        authResponse.setHttpOnlyCookie(response);
        return ResponseEntity.status(authResponse.getStatus()).body(authResponse);
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<AuthenticatorResponse> registerAdmin(@RequestBody AdminRegistrationRequest request, HttpServletResponse response) {
        AuthenticatorResponse authResponse = authenticationService.registerAdmin(request);
        authResponse.setHttpOnlyCookie(response);
        return ResponseEntity.status(authResponse.getStatus()).body(authResponse);
    }

    @PostMapping("/authenticateStudent")
    public ResponseEntity<AuthenticatorResponse> authenticateStudent(@RequestBody AuthenticationRequest request, HttpServletResponse response) {
        AuthenticatorResponse authResponse = authenticationService.authenticateStudent(request);
        authResponse.setHttpOnlyCookie(response);
        return ResponseEntity.status(authResponse.getStatus()).body(authResponse);
    }

    @PostMapping("/authenticateParent")
    public ResponseEntity<AuthenticatorResponse> authenticateParent(@RequestBody AuthenticationRequest request, HttpServletResponse response) {
        AuthenticatorResponse authResponse = authenticationService.authenticateParent(request);
        authResponse.setHttpOnlyCookie(response);
        return ResponseEntity.status(authResponse.getStatus()).body(authResponse);
    }

    @PostMapping("/authenticateDriver")
    public ResponseEntity<AuthenticatorResponse> authenticateDriver(@RequestBody AuthenticationRequest request, HttpServletResponse response) {
        AuthenticatorResponse authResponse = authenticationService.authenticateDriver(request);
        authResponse.setHttpOnlyCookie(response);
        return ResponseEntity.status(authResponse.getStatus()).body(authResponse);
    }

    @PostMapping("/authenticateAdmin")
    public ResponseEntity<AuthenticatorResponse> authenticateAdmin(@RequestBody AuthenticationRequest request, HttpServletResponse response) {
        AuthenticatorResponse authResponse = authenticationService.authenticateAdmin(request);
        authResponse.setHttpOnlyCookie(response);
        return ResponseEntity.status(authResponse.getStatus()).body(authResponse);
    }

    @GetMapping("/check")
    public ResponseEntity<JwtCheckResponse> checkAuth(@RequestAttribute String userEmail) {
        JwtCheckResponse JWTResponse = authenticationService.checkAuth(userEmail);
        return ResponseEntity.status(JWTResponse.getStatus()).body(JWTResponse);
    }

}