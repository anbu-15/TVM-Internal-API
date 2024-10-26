package com.tvm.internal.edit.serviceImpl;

import com.tvm.internal.edit.model.Onboarding;
import com.tvm.internal.edit.repo.OnboardingRepository;
import com.tvm.internal.edit.service.OnboardingService;
import com.tvm.internal.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class OnboardingServiceImp implements OnboardingService {

    @Autowired
    private OnboardingRepository onboardingRepository;

    public List<Onboarding> getAllOnboardings() {
        return onboardingRepository.findAll();
    }

    public Onboarding getOnboardingById(UUID id) {
        return onboardingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Onboarding process not found"));
    }

    public Onboarding createOnboarding(Onboarding onboarding) {
        return onboardingRepository.save(onboarding);
    }

    public Onboarding updateOnboarding(UUID id, Onboarding onboardingDetails) {
        Onboarding onboarding = getOnboardingById(id);
        onboarding.setStatus(onboardingDetails.getStatus());
        onboarding.setMentor(onboardingDetails.getMentor());
        onboarding.setTasks(onboardingDetails.getTasks());
        return onboardingRepository.save(onboarding);
    }

    public void deleteOnboarding(UUID id) {
        onboardingRepository.deleteById(id);
    }
}

