package com.habbypanda.bus_mangment_system.auth;

import com.habbypanda.bus_mangment_system.user.driver.DriverRepository;
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


    @PostMapping("/registerStudent")
    public ResponseEntity<AuthenticatorResponse> registerStudent(@RequestBody StudentRegistrationRequest request){
        AuthenticatorResponse response = authenticationService.registerStudent(request);
        return ResponseEntity.status(response.getStatus()).body(response);//TODO see a better way to send response cause it displays the data fields of class AuthenticatorResponse
    }
    @PostMapping("/registerParent")
    public ResponseEntity<AuthenticatorResponse> registerParent(@RequestBody ParentRegistrationRequest request){
        AuthenticatorResponse response = authenticationService.registerParent(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @PostMapping("/registerDriver")
    public ResponseEntity<AuthenticatorResponse> registerDriver(@RequestBody DriverRegistrationRequest request){
        AuthenticatorResponse response = authenticationService.registerDriver(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    //TODO this api is accessible to the public, should be restricted to only admin
    @PostMapping("/registerAdmin")
    public ResponseEntity<AuthenticatorResponse> registerAdmin(@RequestBody AdminRegistrationRequest request){
        AuthenticatorResponse response = authenticationService.registerAdmin(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }



    @PostMapping("/authenticateStudent")
    public ResponseEntity<AuthenticatorResponse> authenticateStudent(@RequestBody AuthenticationRequest request){
        AuthenticatorResponse response = authenticationService.authenticateStudent(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @PostMapping("/authenticateParent")
    public ResponseEntity<AuthenticatorResponse> authenticateParent(@RequestBody AuthenticationRequest request){
        AuthenticatorResponse response = authenticationService.authenticateParent(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }@PostMapping("/authenticateDriver")
    public ResponseEntity<AuthenticatorResponse> authenticateDriver(@RequestBody AuthenticationRequest request){
        AuthenticatorResponse response = authenticationService.authenticateDriver(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @PostMapping("/authenticateAdmin")
    public ResponseEntity<AuthenticatorResponse> authenticateAdmin(@RequestBody AuthenticationRequest request){
        AuthenticatorResponse response = authenticationService.authenticateAdmin(request);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
