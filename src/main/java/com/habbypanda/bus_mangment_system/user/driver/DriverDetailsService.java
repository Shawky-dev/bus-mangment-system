package com.habbypanda.bus_mangment_system.user.driver;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Log
@Service
@RequiredArgsConstructor
public class DriverDetailsService implements UserDetailsService {
    private final DriverRepository driverRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.driverRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
