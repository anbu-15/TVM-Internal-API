package com.tvm.internal.edit.controller;

import com.tvm.internal.edit.model.Onboarding;
import com.tvm.internal.edit.service.OnboardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class OnboardingController {

    @Autowired
    private OnboardingService onboardingService;

    @GetMapping("/onboarding/getAllOnboardedUsers")
    public List<Onboarding> getAllOnboardings() {
        return onboardingService.getAllOnboardings();
    }

    @GetMapping("/onboarding/{id}")
    public Onboarding getOnboardingById(@PathVariable UUID id) {
        return onboardingService.getOnboardingById(id);
    }

    @PostMapping("/onboarding")
    public Onboarding createOnboarding(@RequestBody Onboarding onboarding) {
        return onboardingService.createOnboarding(onboarding);
    }

    @PutMapping("/onboarding/{id}")
    public Onboarding updateOnboarding(@PathVariable UUID id, @RequestBody Onboarding onboarding) {
        return onboardingService.updateOnboarding(id, onboarding);
    }

    @DeleteMapping("/onboarding/{id}")
    public void deleteOnboarding(@PathVariable UUID id) {
        onboardingService.deleteOnboarding(id);
    }
}

