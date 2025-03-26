package com.badgesservice.service_badge.services;

import com.badgesservice.service_badge.entities.User;
import com.badgesservice.service_badge.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User update(Long id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setFirstName(userDetails.getFirstName());
            user.setLastName(userDetails.getLastName());
            user.setEmail(userDetails.getEmail());
            // Update other fields as necessary
            return userRepository.save(user);
        }).orElse(null);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

}