package com.habbypanda.bus_mangment_system.auth;

import com.habbypanda.bus_mangment_system.config.JwtService;
import com.habbypanda.bus_mangment_system.user.*;
import com.habbypanda.bus_mangment_system.user.admin.Admin;
import com.habbypanda.bus_mangment_system.user.admin.AdminRepository;
import com.habbypanda.bus_mangment_system.user.driver.Driver;
import com.habbypanda.bus_mangment_system.user.driver.DriverRepository;
import com.habbypanda.bus_mangment_system.user.parent.Parent;
import com.habbypanda.bus_mangment_system.user.parent.ParentRepository;
import com.habbypanda.bus_mangment_system.user.student.Student;
import com.habbypanda.bus_mangment_system.user.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final StudentRepository studentRepository;
    private final ParentRepository parentRepository;
    private final DriverRepository driverRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ComposedDetailsService composedDetailsService;


    public AuthenticatorResponse registerParent(ParentRegistrationRequest request) {
        if (request.getName() == null || request.getEmail() == null || request.getPassword() == null) {
            return AuthenticatorResponse.builder().message("Invalid credentials").status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            composedDetailsService.loadUserByUsername(request.getEmail());
            return AuthenticatorResponse.builder().message("user already exists").status(HttpStatus.CONFLICT).build();
        } catch (UsernameNotFoundException e) {
            // User not found, proceed with registration
        }
        Parent user = Parent.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        parentRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticatorResponse
                .builder()
                .jwt(jwtToken)
                .message(user.getClass().getSimpleName() +" account created successfully")
                .status(HttpStatus.CREATED)
                .build();
    }

    public AuthenticatorResponse registerStudent(StudentRegistrationRequest request) {
        if (request.getName() == null || request.getEmail() == null || request.getPassword() == null) {
            return AuthenticatorResponse.builder().message("Invalid credentials").status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            composedDetailsService.loadUserByUsername(request.getEmail());
            return AuthenticatorResponse.builder().message("user already exists").status(HttpStatus.CONFLICT).build();
        } catch (UsernameNotFoundException e) {
            // User not found, proceed with registration
        }
        Student user = Student.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        studentRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticatorResponse.builder().jwt(jwtToken).message(user.getClass().getSimpleName() +" created successfully").status(HttpStatus.CREATED).build();
    }

    public AuthenticatorResponse registerDriver(DriverRegistrationRequest request) {
        if (request.getName() == null || request.getEmail() == null || request.getPassword() == null) {
            return AuthenticatorResponse.builder().message("Invalid credentials").status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            composedDetailsService.loadUserByUsername(request.getEmail());
            return AuthenticatorResponse.builder().message("user already exists").status(HttpStatus.CONFLICT).build();
        } catch (UsernameNotFoundException e) {
            // User not found, proceed with registration
        }
        Driver user = Driver.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        driverRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticatorResponse.builder().jwt(jwtToken).message(user.getClass().getSimpleName() +" created successfully").status(HttpStatus.CREATED).build();
    }
    public AuthenticatorResponse registerAdmin(AdminRegistrationRequest request) {
        if (request.getName() == null || request.getEmail() == null || request.getPassword() == null) {
            return AuthenticatorResponse.builder().message("Invalid credentials").status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            composedDetailsService.loadUserByUsername(request.getEmail());
            return AuthenticatorResponse.builder().message("user already exists").status(HttpStatus.CONFLICT).build();
        } catch (UsernameNotFoundException e) {
            // User not found, proceed with registration
        }
        Admin user = Admin.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        adminRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticatorResponse.builder().jwt(jwtToken).message(user.getClass().getSimpleName() +" created successfully").status(HttpStatus.CREATED).build();
    }





    public AuthenticatorResponse authenticateStudent(AuthenticationRequest request) {
        if (request.getEmail() == null || request.getPassword() == null) {
            return AuthenticatorResponse.builder().message("Invalid credentials").status(HttpStatus.BAD_REQUEST).build();
        }


        //will autmaticaly throw an exception if email not found or password is wrong
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = studentRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticatorResponse.builder().jwt(jwtToken).status(HttpStatus.ACCEPTED).message(user.getClass().getSimpleName() +" authenticated successfully").build();
    }
    public AuthenticatorResponse authenticateParent(AuthenticationRequest request) {
        if (request.getEmail() == null || request.getPassword() == null) {
            return AuthenticatorResponse.builder().message("Invalid credentials").status(HttpStatus.BAD_REQUEST).build();
        } //will autmaticaly throw an exception if email not found or password is wrong
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = parentRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticatorResponse.builder().jwt(jwtToken).status(HttpStatus.ACCEPTED).message(user.getClass().getSimpleName() +" authenticated successfully").build();
    }
    public AuthenticatorResponse authenticateDriver(AuthenticationRequest request) {
        if (request.getEmail() == null || request.getPassword() == null) {
            return AuthenticatorResponse.builder().message("Invalid credentials").status(HttpStatus.BAD_REQUEST).build();
        } //will autmaticaly throw an exception if email not found or password is wrong
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = driverRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticatorResponse.builder().jwt(jwtToken).status(HttpStatus.ACCEPTED).message(user.getClass().getSimpleName() +" authenticated successfully").build();
    }
    public AuthenticatorResponse authenticateAdmin(AuthenticationRequest request) {
        if (request.getEmail() == null || request.getPassword() == null) {
            return AuthenticatorResponse.builder().message("Invalid credentials").status(HttpStatus.BAD_REQUEST).build();
        } //will autmaticaly throw an exception if email not found or password is wrong
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = adminRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticatorResponse.builder().jwt(jwtToken).status(HttpStatus.ACCEPTED).message(user.getClass().getSimpleName() +" authenticated successfully").build();
    }


    public JwtCheckResponse checkAuth(String userEmail) {
        UserDetails userDetails = composedDetailsService.loadUserByUsername(userEmail);
        User user = (User) userDetails;
        return JwtCheckResponse.builder().role(user.getRole()).email(user.getEmail()).message("Authorized").status(HttpStatus.OK).build();
    }
}
