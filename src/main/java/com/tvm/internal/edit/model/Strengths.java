package com.tvm.internal.edit.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Strengths {
        private String keyStrengths;
        private String examples;
    }