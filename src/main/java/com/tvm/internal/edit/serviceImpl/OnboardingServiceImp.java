package com.tvm.internal.edit.serviceImpl;

import com.tvm.internal.edit.model.Onboarding;
import com.tvm.internal.edit.model.OnboardingTask;
import com.tvm.internal.edit.repo.OnboardingRepository;
import com.tvm.internal.edit.service.OnboardingService;
import com.tvm.internal.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OnboardingServiceImp implements OnboardingService {

    @Autowired
    private OnboardingRepository onboardingRepository;

    public List<Onboarding> getAllOnboardings() {
        return onboardingRepository.findAll();
    }

    public Onboarding getOnboardingById(Long id) {
        return onboardingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Onboarding process not found"));
    }

    public Onboarding createOnboarding(Onboarding onboarding) {
        return onboardingRepository.save(onboarding);
    }

//    public Onboarding updateOnboarding(Long id, Onboarding onboardingDetails) {
//        Onboarding onboarding = getOnboardingById(id);
//        onboarding.setStatus(onboardingDetails.getStatus());
//        onboarding.setMentor(onboardingDetails.getMentor());
////        onboarding.setTasks(onboardingDetails.getTasks());
//        return onboardingRepository.save(onboarding);
//    }

    public Onboarding updateOnboarding(Long id, Onboarding onboardingDetails) {
        Onboarding onboarding = getOnboardingById(id);
        onboarding.setStatus(onboardingDetails.getStatus());
        onboarding.setMentor(onboardingDetails.getMentor());

        // Update existing tasks or add new ones
        List<OnboardingTask> existingTasks = onboarding.getTasks();

        // Clear existing tasks to avoid duplication in case of updates
        existingTasks.clear();

        // Loop through the provided tasks and update or add them
        for (OnboardingTask task : onboardingDetails.getTasks()) {
            // Assuming taskId is set in onboardingDetails, check if the task exists
            Optional<OnboardingTask> existingTaskOpt = existingTasks.stream()
                    .filter(t -> t.getTaskId().equals(task.getTaskId()))
                    .findFirst();

            if (existingTaskOpt.isPresent()) {
                // Update the existing task
                OnboardingTask existingTask = existingTaskOpt.get();
                existingTask.setDescription(task.getDescription());
                existingTask.setDueDate(task.getDueDate());
                existingTask.setStatus(task.getStatus());
            } else {
                // If the task doesn't exist, add it to the list
                existingTasks.add(task);
            }
        }

        // Save the updated onboarding entity
        return onboardingRepository.save(onboarding);
    }



    public void deleteOnboarding(Long id) {
        onboardingRepository.deleteById(id);
    }
}
