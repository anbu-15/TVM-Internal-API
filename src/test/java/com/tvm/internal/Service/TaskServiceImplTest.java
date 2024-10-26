package com.tvm.internal.Service;

import com.tvm.internal.edit.model.Tasks;
import com.tvm.internal.edit.repo.TaskRepo;
import com.tvm.internal.edit.serviceImpl.TaskServiceImpl;
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

class TaskServiceImplTest {

    @Mock
    private TaskRepo taskRepo;

    @InjectMocks
    private TaskServiceImpl taskService;

    private Tasks task;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        task = new Tasks();
        task.setTaskId(1);  // Assuming Tasks has an 'id' field
        task.setTaskOwner("Alice");
        task.setTaskName("Complete report");
        task.setDescription("Finish the annual report by the end of the month");
        task.setPriority("High");
        task.setStatus("In Progress");
    }

    @Test
    void getAllTasks() {
        when(taskRepo.findAll()).thenReturn(Arrays.asList(task));

        List<Tasks> tasks = taskService.getAllTasks();
        assertEquals(1, tasks.size());
        assertEquals("Complete report", tasks.get(0).getTaskName());
        verify(taskRepo, times(1)).findAll();
    }

    @Test
    void getTaskById_existingId() {
        when(taskRepo.findById(1)).thenReturn(Optional.of(task));

        Optional<Tasks> foundTask = taskService.getTaskById(1);
        assertTrue(foundTask.isPresent());
        assertEquals("Complete report", foundTask.get().getTaskName());
        verify(taskRepo, times(1)).findById(1);
    }

    @Test
    void getTaskById_nonExistingId() {
        when(taskRepo.findById(2)).thenReturn(Optional.empty());

        Optional<Tasks> foundTask = taskService.getTaskById(2);
        assertFalse(foundTask.isPresent());
        verify(taskRepo, times(1)).findById(2);
    }

    @Test
    void createTask() {
        when(taskRepo.save(task)).thenReturn(task);

        Tasks createdTask = taskService.createTask(task);
        assertNotNull(createdTask);
        assertEquals("Complete report", createdTask.getTaskName());
        verify(taskRepo, times(1)).save(task);
    }

    @Test
    void updateTask_existingId() {
        when(taskRepo.findById(1)).thenReturn(Optional.of(task));
        when(taskRepo.save(any(Tasks.class))).thenReturn(task);

        Tasks updatedTask = new Tasks();
        updatedTask.setTaskOwner("Bob");
        updatedTask.setTaskName("Finish presentation");
        updatedTask.setDescription("Prepare slides for next week's meeting");
        updatedTask.setPriority("Medium");
        updatedTask.setStatus("Pending");

        Tasks result = taskService.updateTask(1, updatedTask);
        assertNotNull(result);
        assertEquals("Finish presentation", result.getTaskName());
        verify(taskRepo, times(1)).findById(1);
        verify(taskRepo, times(1)).save(any(Tasks.class));
    }

    @Test
    void updateTask_nonExistingId() {
        when(taskRepo.findById(2)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            taskService.updateTask(2, task);
        });
        assertEquals("Task not found", exception.getMessage());
        verify(taskRepo, times(1)).findById(2);
    }

    @Test
    void deleteTask() {
        doNothing().when(taskRepo).deleteById(1);

        taskService.deleteTask(1);
        verify(taskRepo, times(1)).deleteById(1);
    }
}
