package com.tvm.internal.edit.controller;

import com.tvm.internal.edit.model.UserSignup;
import com.tvm.internal.edit.service.UserSignupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usersignup")
public class UserSignupController {
    private static final Logger logger = LoggerFactory.getLogger(UserSignupController.class);

    @Autowired
    private UserSignupService userSignupService;

    @PostMapping
    public ResponseEntity<UserSignup> createUser(@RequestBody UserSignup userSignup) {
        UserSignup createdUser = userSignupService.createUser(userSignup);
        logger.info("User created with ID: {}", createdUser.getId());
        return ResponseEntity.status(HttpStatus.OK).body(createdUser);
    }

    @GetMapping
    public ResponseEntity<List<UserSignup>> getAllUsers() {
        logger.info("Fetching all users");
        return ResponseEntity.ok(userSignupService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        if (!userSignupService.existsById(id)) {
            logger.warn("User not found with ID: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with ID: " + id);
        }
        logger.info("Fetching user with ID: {}", id);
        return ResponseEntity.ok(userSignupService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserSignup userSignup) {
        UserSignup updatedUser = userSignupService.updateUser(id, userSignup);
        if (updatedUser == null) {
            logger.warn("User not found with ID: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with ID: " + id);
        }
        logger.info("Successfully updated User record with ID: {}", id);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        ResponseEntity<String> response = userSignupService.deleteUser(id);
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            logger.warn("User not found with ID: {}", id);
            return response;
        }
        logger.info("Deleted user with ID: {}", id);
        return response;
    }
}