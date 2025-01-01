package com.habbypanda.bus_mangment_system.user;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserManipulation<T extends User> {
    private final UserRepository<T,Integer> userRepository;
    public List<T> findAll() {
        return userRepository.findAll();
    }
    public Optional<T> findById(Integer id) {
        return userRepository.findById(id);
    }
    public T save(T user) {
        return userRepository.save(user);
    }
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
    public T update(T newUser) {
        if(!userRepository.existsById(newUser.getId())) {
            throw new IllegalArgumentException("User with id " + newUser.getId() + " does not exist");
        }else {
            userRepository.deleteById(newUser.getId());

        return userRepository.save(newUser);
        }
    }

}
