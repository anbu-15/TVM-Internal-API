package com.tvm.internal.edit.service;

import com.tvm.internal.edit.model.Onboarding;

import java.util.List;

public interface OnboardingService {
    List<Onboarding> getAllOnboardings();
    Onboarding getOnboardingById(Long id); // Change to Long
    Onboarding createOnboarding(Onboarding onboarding);
    Onboarding updateOnboarding(Long id, Onboarding onboardingDetails); // Change to Long
    void deleteOnboarding(Long id); // Change to Long
}
