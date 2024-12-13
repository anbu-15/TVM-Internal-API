package com.tvm.internal.edit.service;

import com.tvm.internal.edit.model.Users;
import com.tvm.internal.edit.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;

    public Users registerUser(Users user) {
        logger.info("Registering user: {}", user.getUsername());

        if (userRepository.findByUsername(user.getUsername()) != null) {
            logger.warn("Registration failed: Username '{}' already exists.", user.getUsername());
            throw new IllegalArgumentException("Username already exists");
        }

        // Encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the new user to the database
        Users savedUser = userRepository.save(user);
        logger.info("User registered successfully with ID: {}", savedUser.getId());

        return savedUser;
    }

    public String verify(Users user) {
        logger.info("Verifying user: {}", user.getUsername());

        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );

            if (authentication.isAuthenticated()) {
                logger.info("User authenticated successfully");

                Users authenticatedUser = userRepository.findByUsername(user.getUsername());
                logger.info("Fetching roles for user: {}", authenticatedUser.getRoles());

                return jwtService.generateToken(user.getUsername(), authenticatedUser.getRoles());
            }
        } catch (Exception e) {
            logger.error("Authentication failed for user: {}", user.getUsername(), e);
            return "fail";
        }
        return "fail";
    }

    public List<Map<String, String>> getAllUserDetails() {
        List<Users> rawData = userRepository.findAll();

        List<Map<String, String>> userDetails = new ArrayList<>();

        for (Users data : rawData) {
            Map<String, String> userMap = new HashMap<>();
            userMap.put("username", data.getUsername());
            userMap.put("roles", data.getRoles());
            userDetails.add(userMap);
        }
        return userDetails;
    }

}
