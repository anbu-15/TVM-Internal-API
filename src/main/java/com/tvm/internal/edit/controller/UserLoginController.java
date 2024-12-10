package com.tvm.internal.edit.controller;

import com.tvm.internal.edit.model.UserLogin;
import com.tvm.internal.edit.service.UserLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userlogin")
public class UserLoginController {
    private static final Logger logger = LoggerFactory.getLogger(UserLoginController.class);

    @Autowired
    private UserLoginService userLoginService;

    @PostMapping
    public ResponseEntity<UserLogin> createUser(@RequestBody UserLogin userLogin) {
        UserLogin createdUser = userLoginService.createUser(userLogin);
        logger.info("User created with ID: {}", createdUser.getId());
        return ResponseEntity.status(HttpStatus.OK).body(createdUser);
    }

    @GetMapping
    public ResponseEntity<List<UserLogin>> getAllUsers() {
        logger.info("Fetching all users");
        return ResponseEntity.ok(userLoginService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        logger.info("Fetching user with ID: {}", id);
        UserLogin userLogin = userLoginService.getUserById(id);
        if (userLogin == null) {
            logger.warn("User not found with ID: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with ID: " + id);
        }
        logger.info("Successfully retrieved user with ID: {}", id);
        return ResponseEntity.ok(userLogin);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserLogin userLogin) {
        UserLogin updatedUser = userLoginService.updateUser(id, userLogin);
        if (updatedUser == null) {
            logger.warn("User not found with ID: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with ID: " + id);
        }
        logger.info("User updated with ID: {}", id);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        ResponseEntity<String> response = userLoginService.deleteUser(id);
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            logger.warn("User not found with ID: {}", id);
            return response;
        }
        logger.info("User deleted with ID: {}", id);
        return response;
    }
}
