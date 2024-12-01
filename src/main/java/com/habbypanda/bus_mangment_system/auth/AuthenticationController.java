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
    public ResponseEntity<AuthenticatorResponse> register(@RequestBody RegistrationRequest request){
        AuthenticatorResponse response = authenticationService.register(request);
        return ResponseEntity.status(response.getStatus()).body(response);//TODO see a better way to send response cause it displays the data fields of class AuthenticatorResponse
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticatorResponse> authenticate(@RequestBody AuthenticationRequest request){
        AuthenticatorResponse response = authenticationService.authenticate(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
