package com.tvm.internal.edit.service;

import com.tvm.internal.edit.model.Goal;

import java.util.List;
import java.util.Optional;

public interface GoalService {

     List<Goal> getAllGoals();
     Optional<Goal> getGoalById(Long id);
     Goal createGoal(Goal goal);
     Goal updateGoal(Long id, Goal updatedGoal);
     void deleteGoal(Long id);
}
