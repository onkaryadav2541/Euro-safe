package com.student.eurosafe.service;

import com.student.eurosafe.entity.User;
import com.student.eurosafe.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(User user) {
        // 1. Check if username already exists
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        
        if (existingUser.isPresent()) {
            // If found, stop and throw an error
            throw new RuntimeException("Username '" + user.getUsername() + "' is already taken!");
        }

        // 2. If not found, save the new user
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}