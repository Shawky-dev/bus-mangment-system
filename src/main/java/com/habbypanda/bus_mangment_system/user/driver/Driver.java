package com.habbypanda.bus_mangment_system.user.driver;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.habbypanda.bus_mangment_system.user.Role;
import com.habbypanda.bus_mangment_system.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "drivers")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Driver extends User {
    private Integer driverLicense;


    @Builder
    public Driver(Integer id, String name, String email, String password, Integer driverLicense) {
        super(id, name, email, password, Role.USER);
        this.driverLicense = driverLicense;
    }
}
