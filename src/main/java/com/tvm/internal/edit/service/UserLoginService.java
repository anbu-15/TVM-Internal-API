package com.tvm.internal.edit.service;

import com.tvm.internal.edit.model.UserLogin;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserLoginService {
    UserLogin createUser(UserLogin userLogin);

    List<UserLogin> getAllUsers();

    UserLogin getUserById(Long id);

    UserLogin updateUser(Long id, UserLogin userLogin);

    ResponseEntity<String> deleteUser(Long id);

    boolean existsById(Long id);
}
