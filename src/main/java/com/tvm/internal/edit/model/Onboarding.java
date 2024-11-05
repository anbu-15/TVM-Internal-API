package com.tvm.internal.edit.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Onboarding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Use IDENTITY for Long
    private Long onboardingId;

    private UUID employeeId;
    private String startDate;
    private String status;
    private UUID mentor;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "onboarding_id")
    private List<OnboardingTask> tasks;

}
