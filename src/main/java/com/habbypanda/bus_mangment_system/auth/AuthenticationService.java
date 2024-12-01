package com.habbypanda.bus_mangment_system.auth;

import com.habbypanda.bus_mangment_system.config.JwtService;
import com.habbypanda.bus_mangment_system.user.Role;
import com.habbypanda.bus_mangment_system.user.User;
import com.habbypanda.bus_mangment_system.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticatorResponse register(RegistrationRequest request) {
        if (request.getName() == null || request.getEmail() == null || request.getPassword() == null) {
            return AuthenticatorResponse.builder().message("Invalid credentials").status(HttpStatus.BAD_REQUEST).build();
        }
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return AuthenticatorResponse.builder().message("user already exists").status(HttpStatus.CONFLICT).build();
        }
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticatorResponse.builder().jwt(jwtToken).message("user created succesfully").status(HttpStatus.CREATED).build();
    }

    public AuthenticatorResponse authenticate(AuthenticationRequest request) {
        if (request.getEmail() == null || request.getPassword() == null) {
            return AuthenticatorResponse.builder().message("Invalid credentials").status(HttpStatus.BAD_REQUEST).build();
        } //will autmaticaly throw an exception if email not found or password is wrong
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticatorResponse.builder().jwt(jwtToken).status(HttpStatus.ACCEPTED).message("user authenticated successfully").build();
    }
}
