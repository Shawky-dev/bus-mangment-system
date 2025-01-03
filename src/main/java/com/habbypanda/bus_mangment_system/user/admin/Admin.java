package com.habbypanda.bus_mangment_system.user.admin;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.habbypanda.bus_mangment_system.user.Role;
import com.habbypanda.bus_mangment_system.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "admins")

public class Admin extends User {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;


    @Builder
    public Admin(Integer id, String name, String email, String password) {
        super(id, name, email, password, Role.ADMIN);
    }
}
