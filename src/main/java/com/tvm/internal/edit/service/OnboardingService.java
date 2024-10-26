package com.tvm.internal.edit.service;

import com.tvm.internal.edit.model.Onboarding;

import java.util.List;
import java.util.UUID;

public interface OnboardingService {
    List<Onboarding> getAllOnboardings();
    Onboarding getOnboardingById(UUID id);
    Onboarding createOnboarding(Onboarding onboarding);
    Onboarding updateOnboarding(UUID id, Onboarding onboardingDetails);
    void deleteOnboarding(UUID id);
}
