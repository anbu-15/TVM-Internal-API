package com.tvm.internal.edit.serviceImpl;

import com.tvm.internal.edit.model.UserLogin;
import com.tvm.internal.edit.repo.UserLoginRepository;
import com.tvm.internal.edit.service.UserLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    private static final Logger logger = LoggerFactory.getLogger(UserLoginServiceImpl.class);

    @Autowired
    private UserLoginRepository userLoginRepository;

    @Override
    public UserLogin createUser(UserLogin userLogin) {
        logger.info("Saving new user with ID: {}", userLogin.getId());
        return userLoginRepository.save(userLogin);
    }

    @Override
    public List<UserLogin> getAllUsers() {
        logger.info("Fetching all users");
        return userLoginRepository.findAll();
    }

    @Override
    public UserLogin getUserById(Long id) {
        logger.info("Fetching user with ID: {}", id);
        Optional<UserLogin> userLogin = userLoginRepository.findById(id);
        if (userLogin.isEmpty()) {
            logger.error("User not found with ID: {}", id);
            return null;
        }
        logger.info("Successfully retrieved user with ID: {}", id);
        return userLogin.get(); // Return the user if found
    }

    @Override
    public UserLogin updateUser(Long id, UserLogin userLogin) {
        UserLogin existingUser = userLoginRepository.findById(id).orElse(null);
        if (existingUser == null) {
            logger.warn("User not found with ID: {}", id);
            return null;
        }
        logger.info("Updating user with ID: {}", id);
        existingUser.setEmail(userLogin.getEmail());
        existingUser.setPhone(userLogin.getPhone());
        existingUser.setPassword(userLogin.getPassword());
        return userLoginRepository.save(existingUser);
    }

    @Override
    public ResponseEntity<String> deleteUser(Long id) {
        if (!userLoginRepository.existsById(id)) {
            logger.warn("User not found with ID: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with ID: " + id);
        }
        userLoginRepository.deleteById(id);
        logger.info("Deleted user with ID: {}", id);
        return ResponseEntity.ok("User successfully deleted");
    }

    @Override
    public boolean existsById(Long id) {
        return userLoginRepository.existsById(id);
    }
}

