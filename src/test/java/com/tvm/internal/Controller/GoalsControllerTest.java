package com.tvm.internal.Controller;

import com.tvm.internal.edit.controller.GoalsController;
import com.tvm.internal.edit.model.Goal;
import com.tvm.internal.edit.serviceImpl.GoalServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class GoalsControllerTest {

    @InjectMocks
    private GoalsController goalsController;

    @Mock
    private GoalServiceImpl goalService;

    private Goal goal;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        goal = new Goal();
        goal.setGoalId(1L);
        goal.setName("Complete Project");
        goal.setDescription("Finish the ongoing project by the end of the month.");
        goal.setPriority("High");
        goal.setStartDate(LocalDate.now());
        goal.setEndDate(LocalDate.now().plusDays(30));
        goal.setProgress(0);
    }

    @Test
    void testGetAllGoals() {
        List<Goal> goalsList = new ArrayList<>();
        goalsList.add(goal);

        when(goalService.getAllGoals()).thenReturn(goalsList);

        ResponseEntity<List<Goal>> response = goalsController.getAllGoals();

        assertThat(response).isNotNull();
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).hasSize(1);
        assertThat(response.getBody().get(0)).isEqualTo(goal);
        verify(goalService, times(1)).getAllGoals();
    }

    @Test
    void testGetGoalById() {
        when(goalService.getGoalById(anyLong())).thenReturn(Optional.of(goal));

        ResponseEntity<Goal> response = goalsController.getGoalById(1L);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(goal);
        verify(goalService, times(1)).getGoalById(1L);
    }

    @Test
    void testGetGoalByIdNotFound() {
        when(goalService.getGoalById(anyLong())).thenReturn(Optional.empty());

        ResponseEntity<Goal> response = goalsController.getGoalById(999L);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCodeValue()).isEqualTo(404);
        verify(goalService, times(1)).getGoalById(999L);
    }

    @Test
    void testCreateGoal() {
        when(goalService.createGoal(any(Goal.class))).thenReturn(goal);

        ResponseEntity<Goal> response = goalsController.createGoal(goal);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(goal);
        verify(goalService, times(1)).createGoal(any(Goal.class));
    }

    @Test
    void testUpdateGoal() {
        when(goalService.updateGoal(anyLong(), any(Goal.class))).thenReturn(goal);

        ResponseEntity<Goal> response = goalsController.updateGoal(1L, goal);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(goal);
        verify(goalService, times(1)).updateGoal(anyLong(), any(Goal.class));
    }

    @Test
    void testDeleteGoal() {
        doNothing().when(goalService).deleteGoal(anyLong());

        ResponseEntity<Void> response = goalsController.deleteGoal(1L);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCodeValue()).isEqualTo(204);
        verify(goalService, times(1)).deleteGoal(1L);
    }
}
