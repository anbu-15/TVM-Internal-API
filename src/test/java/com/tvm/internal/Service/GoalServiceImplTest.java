package com.tvm.internal.Service;

import com.tvm.internal.edit.model.Goal;
import com.tvm.internal.edit.repo.GoalRepo;
import com.tvm.internal.edit.serviceImpl.GoalServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GoalServiceImplTest {

    @Mock
    private GoalRepo goalRepo;

    @InjectMocks
    private GoalServiceImpl goalService;

    private Goal goal;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        goal = new Goal();
        goal.setGoalId(1L);
        goal.setName("Test Goal");
        goal.setDescription("Test Description");
        goal.setPriority("high");
        goal.setStartDate(LocalDate.now());
        goal.setEndDate(LocalDate.now().plusDays(10));
        goal.setProgress(50);
    }

    @Test
    void getAllGoals() {
        List<Goal> goals = Arrays.asList(goal);
        when(goalRepo.findAll()).thenReturn(goals);

        List<Goal> result = goalService.getAllGoals();

        assertEquals(1, result.size());
        assertEquals("Test Goal", result.get(0).getName());
        verify(goalRepo, times(1)).findAll();
    }

    @Test
    void getGoalById_existingId() {
        when(goalRepo.findById(1L)).thenReturn(Optional.of(goal));

        Optional<Goal> result = goalService.getGoalById(1L);

        assertTrue(result.isPresent());
        assertEquals("Test Goal", result.get().getName());
        verify(goalRepo, times(1)).findById(1L);
    }

    @Test
    void getGoalById_nonExistingId() {
        when(goalRepo.findById(2L)).thenReturn(Optional.empty());

        Optional<Goal> result = goalService.getGoalById(2L);

        assertFalse(result.isPresent());
        verify(goalRepo, times(1)).findById(2L);
    }

    @Test
    void createGoal() {
        when(goalRepo.save(goal)).thenReturn(goal);

        Goal result = goalService.createGoal(goal);

        assertNotNull(result);
        assertEquals("Test Goal", result.getName());
        verify(goalRepo, times(1)).save(goal);
    }

    @Test
    void updateGoal_existingId() {
        Goal updatedGoal = new Goal();
        updatedGoal.setName("Updated Goal");
        updatedGoal.setDescription("Updated Description");
        updatedGoal.setPriority("high");
        updatedGoal.setStartDate(LocalDate.now().plusDays(1));
        updatedGoal.setEndDate(LocalDate.now().plusDays(15));
        updatedGoal.setProgress(75);

        when(goalRepo.findById(1L)).thenReturn(Optional.of(goal));
        when(goalRepo.save(goal)).thenReturn(goal);

        Goal result = goalService.updateGoal(1L, updatedGoal);

        assertEquals("Updated Goal", result.getName());
        assertEquals("Updated Description", result.getDescription());
        assertEquals("high", result.getPriority());
        assertEquals(75, result.getProgress());
        verify(goalRepo, times(1)).findById(1L);
        verify(goalRepo, times(1)).save(goal);
    }

    @Test
    void updateGoal_nonExistingId() {
        Goal updatedGoal = new Goal();
        when(goalRepo.findById(2L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            goalService.updateGoal(2L, updatedGoal);
        });

        assertEquals("Goal not found with id: 2", exception.getMessage());
        verify(goalRepo, times(1)).findById(2L);
    }

    @Test
    void deleteGoal() {
        doNothing().when(goalRepo).deleteById(1L);

        goalService.deleteGoal(1L);

        verify(goalRepo, times(1)).deleteById(1L);
    }
}

