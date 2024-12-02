package com.habbypanda.bus_mangment_system.auth;

import com.habbypanda.bus_mangment_system.config.JwtService;
import com.habbypanda.bus_mangment_system.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final StudentRepository studentRepository;
    private final ParentRepository parentRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticatorResponse registerParent(ParentRegistrationRequest request) {
        if (request.getName() == null || request.getEmail() == null || request.getPassword() == null) {
            return AuthenticatorResponse.builder().message("Invalid credentials").status(HttpStatus.BAD_REQUEST).build();
        }
        if (parentRepository.findByEmail(request.getEmail()).isPresent() || studentRepository.findByEmail(request.getEmail()).isPresent()) {
            return AuthenticatorResponse.builder().message("user already exists").status(HttpStatus.CONFLICT).build();
        }
        Parent user = Parent.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        parentRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticatorResponse.builder().jwt(jwtToken).message("Parent account created succesfully").status(HttpStatus.CREATED).build();
    }

    public AuthenticatorResponse registerStudent(StudentRegistrationRequest request) {
        if (request.getName() == null || request.getEmail() == null || request.getPassword() == null) {
            return AuthenticatorResponse.builder().message("Invalid credentials").status(HttpStatus.BAD_REQUEST).build();
        }
        if (studentRepository.findByEmail(request.getEmail()).isPresent() || parentRepository.findByEmail(request.getEmail()).isPresent()) {
            return AuthenticatorResponse.builder().message("user already exists").status(HttpStatus.CONFLICT).build();
        }
        Student user = Student.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        studentRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticatorResponse.builder().jwt(jwtToken).message("Student created succesfully").status(HttpStatus.CREATED).build();
    }
    public AuthenticatorResponse authenticateStudent(AuthenticationRequest request) {
        if (request.getEmail() == null || request.getPassword() == null) {
            return AuthenticatorResponse.builder().message("Invalid credentials").status(HttpStatus.BAD_REQUEST).build();
        } //will autmaticaly throw an exception if email not found or password is wrong

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = studentRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticatorResponse.builder().jwt(jwtToken).status(HttpStatus.ACCEPTED).message("Student authenticated successfully").build();
    }

    public AuthenticatorResponse authenticateParent(AuthenticationRequest request) {
        if (request.getEmail() == null || request.getPassword() == null) {
            return AuthenticatorResponse.builder().message("Invalid credentials").status(HttpStatus.BAD_REQUEST).build();
        } //will autmaticaly throw an exception if email not found or password is wrong
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = parentRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticatorResponse.builder().jwt(jwtToken).status(HttpStatus.ACCEPTED).message("Parent authenticated successfully").build();
    }
}
