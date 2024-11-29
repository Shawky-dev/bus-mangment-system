package com.habbypanda.bus_mangment_system.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/register")
    public ResponseEntity<AuthenticatiorResponse> register(@RequestBody RegistrationRequest request){
        AuthenticatiorResponse response = authenticationService.register(request);
        return ResponseEntity.status(response.getStatus()).body(response);//TODO see a better way to send repsonse caue it displays the data fields of class AuthenticatiorResponse
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticatiorResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
