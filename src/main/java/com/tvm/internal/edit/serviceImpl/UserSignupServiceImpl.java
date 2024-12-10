package com.tvm.internal.edit.serviceImpl;

import com.tvm.internal.edit.model.UserSignup;
import com.tvm.internal.edit.repo.UserSignupRepository;
import com.tvm.internal.edit.service.UserSignupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSignupServiceImpl implements UserSignupService {
    private static final Logger logger = LoggerFactory.getLogger(UserSignupServiceImpl.class);

    @Autowired
    private UserSignupRepository userSignupRepository;

    @Override
    public UserSignup createUser(UserSignup userSignup) {
        logger.info("Saving new user: {}", userSignup.getUsername());
        return userSignupRepository.save(userSignup);
    }

    @Override
    public List<UserSignup> getAllUsers() {
        logger.info("Fetching all users");
        return userSignupRepository.findAll();
    }

    @Override
    public UserSignup getUserById(Long id) {
        logger.info("Fetching user with ID: {}", id);
        return userSignupRepository.findById(id).orElse(null);
    }

    @Override
    public UserSignup updateUser(Long id, UserSignup userSignup) {
        UserSignup existingUser = userSignupRepository.findById(id).orElse(null);
        if (existingUser == null) {
            logger.warn("User not found with ID: {}", id);
            return null;
        }
        logger.info("Successfully updated User record with ID: {}", id);
        existingUser.setUsername(userSignup.getUsername());
        existingUser.setEmail(userSignup.getEmail());
        existingUser.setPhone(userSignup.getPhone());
        existingUser.setPassword(userSignup.getPassword());
        existingUser.setRole(userSignup.getRole());
        existingUser.setOtp(userSignup.getOtp());
        return userSignupRepository.save(existingUser);
    }

    @Override
    public ResponseEntity<String> deleteUser(Long id) {
        if (!userSignupRepository.existsById(id)) {
            logger.warn("User not found with ID: {}", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with ID: " + id);
        }
        userSignupRepository.deleteById(id);
        logger.info("Deleted user with ID: {}", id);
        return ResponseEntity.ok("User successfully deleted with id: "+ id);
    }

    @Override
    public boolean existsById(Long id) {
        return userSignupRepository.existsById(id);
    }
}
