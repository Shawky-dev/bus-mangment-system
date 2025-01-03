package com.habbypanda.bus_mangment_system.auth;

import com.habbypanda.bus_mangment_system.user.User;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/registerStudent")
    public ResponseEntity<AuthenticatorResponse> registerStudent(@RequestBody StudentRegistrationRequest request, HttpServletResponse response) {
        AuthenticatorResponse authResponse = authenticationService.registerStudent(request);
        return ResponseEntity.status(authResponse.getStatus()).body(authResponse);
    }
    @PostMapping("/registerParent")
    public ResponseEntity<AuthenticatorResponse> registerParent(@RequestBody ParentRegistrationRequest request, HttpServletResponse response) {
        AuthenticatorResponse authResponse = authenticationService.registerParent(request);
        return ResponseEntity.status(authResponse.getStatus()).body(authResponse);
    }

    @PostMapping("/registerDriver")
    public ResponseEntity<AuthenticatorResponse> registerDriver(@RequestBody DriverRegistrationRequest request, HttpServletResponse response) {
        AuthenticatorResponse authResponse = authenticationService.registerDriver(request);
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
    public ResponseEntity<UserResponse<?>> checkAuth(@RequestAttribute String userEmail) {
        UserResponse<?> response = authenticationService.checkAuth(userEmail);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response) {
        AuthenticatorResponse authResponse = new AuthenticatorResponse();
        authResponse.clearHttpOnlyCookie(response);
        return ResponseEntity.status(authResponse.getStatus()).body(authResponse.getMessage());
    }

    @PostMapping("/editStudent/{id}")
    public ResponseEntity<AuthenticatorResponse> editStudent(@PathVariable Integer id, @RequestBody EditUserRequest request) {
        AuthenticatorResponse authResponse = authenticationService.editStudent(id, request);
        return ResponseEntity.status(authResponse.getStatus()).body(authResponse);
    }

    @PostMapping("/editParent/{id}")
    public ResponseEntity<AuthenticatorResponse> editParent(@PathVariable Integer id, @RequestBody EditUserRequest request) {
        AuthenticatorResponse authResponse = authenticationService.editParent(id, request);
        return ResponseEntity.status(authResponse.getStatus()).body(authResponse);
    }

    @PostMapping("/editDriver/{id}")
    public ResponseEntity<AuthenticatorResponse> editDriver(@PathVariable Integer id, @RequestBody EditUserRequest request) {
        AuthenticatorResponse authResponse = authenticationService.editDriver(id, request);
        return ResponseEntity.status(authResponse.getStatus()).body(authResponse);
    }

    @PostMapping("/editAdmin/{id}")
    public ResponseEntity<AuthenticatorResponse> editAdmin(@PathVariable Integer id, @RequestBody EditUserRequest request) {
        AuthenticatorResponse authResponse = authenticationService.editAdmin(id, request);
        return ResponseEntity.status(authResponse.getStatus()).body(authResponse);
    }
}