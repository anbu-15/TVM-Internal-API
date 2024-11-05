package com.tvm.internal.edit.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class OnboardingTask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID taskId;

    private String description;
    private String dueDate;
    private String status;
}
