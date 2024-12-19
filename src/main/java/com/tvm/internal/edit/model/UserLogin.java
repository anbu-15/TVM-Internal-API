package com.tvm.internal.edit.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user_login")
public class UserLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String phone;
    private String password;
}
