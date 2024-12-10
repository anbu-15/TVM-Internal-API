package com.tvm.internal.edit.service;

import com.tvm.internal.edit.model.UserSignup;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserSignupService {

    UserSignup createUser(UserSignup userSignup);

    List<UserSignup> getAllUsers();

    UserSignup getUserById(Long id);

    UserSignup updateUser(Long id, UserSignup userSignup);

    ResponseEntity<String> deleteUser(Long id);

    boolean existsById(Long id);
}
