package com.tvm.internal.edit.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Comment {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String text;
        private String author;
        private String authorType;
        @Column(name = "created_at")
        private LocalDateTime createdAt;

    }

