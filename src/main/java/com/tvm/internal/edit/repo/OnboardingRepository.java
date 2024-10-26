package com.tvm.internal.edit.repo;

import com.tvm.internal.edit.model.Onboarding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OnboardingRepository extends JpaRepository<Onboarding, UUID> {
}
