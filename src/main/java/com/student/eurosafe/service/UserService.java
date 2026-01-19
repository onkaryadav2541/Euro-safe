package com.student.eurosafe.service;

import com.student.eurosafe.entity.User;
import com.student.eurosafe.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Constructor Injection (Best Practice)
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Feature 1: Register a new user
    public User registerUser(User user) {
        // In the future, we will hash the password here
        return userRepository.save(user);
    }

    // Feature 2: Get all users (for testing)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
