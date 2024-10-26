package com.tvm.internal.Controller;

import com.tvm.internal.edit.controller.TaskController;
import com.tvm.internal.edit.model.Tasks;
import com.tvm.internal.edit.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class TaskControllerTest {

    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskService taskService;

    private Tasks task;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        task = new Tasks();
        task.setTaskId(1);
        task.setTaskOwner("John Doe");
        task.setTaskName("Complete project documentation");
        task.setDescription("Documentation for the project must be completed.");
        task.setPriority("High");
        task.setStatus("In Progress");
        task.setStartDate(new Date());
        task.setDueDate(new Date(System.currentTimeMillis() + 86400000)); // 1 day later
        task.setReminder(new Date(System.currentTimeMillis() + 3600000)); // 1 hour later
    }

    @Test
    void testGetAllTasks() {
        List<Tasks> tasksList = new ArrayList<>();
        tasksList.add(task);

        when(taskService.getAllTasks()).thenReturn(tasksList);

        List<Tasks> tasks = taskController.getAllTasks();

        assertThat(tasks).isNotNull();
        assertThat(tasks.size()).isEqualTo(1);
        assertThat(tasks.get(0)).isEqualTo(task);
        verify(taskService, times(1)).getAllTasks();
    }

    @Test
    void testGetTaskById() {
        when(taskService.getTaskById(anyInt())).thenReturn(Optional.of(task));

        ResponseEntity<Tasks> response = taskController.getTaskById(1);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(task);
        verify(taskService, times(1)).getTaskById(1);
    }

    @Test
    void testGetTaskByIdNotFound() {
        when(taskService.getTaskById(anyInt())).thenReturn(Optional.empty());

        ResponseEntity<Tasks> response = taskController.getTaskById(999);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCodeValue()).isEqualTo(404);
        verify(taskService, times(1)).getTaskById(999);
    }

    @Test
    void testCreateTask() {
        when(taskService.createTask(any(Tasks.class))).thenReturn(task);

        Tasks createdTask = taskController.createTask(task);

        assertThat(createdTask).isNotNull();
        assertThat(createdTask.getTaskId()).isEqualTo(task.getTaskId());
        verify(taskService, times(1)).createTask(any(Tasks.class));
    }

    @Test
    void testUpdateTask() {
        when(taskService.updateTask(anyInt(), any(Tasks.class))).thenReturn(task);

        ResponseEntity<Tasks> response = taskController.updateTask(1, task);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(task);
        verify(taskService, times(1)).updateTask(anyInt(), any(Tasks.class));
    }

    @Test
    void testDeleteTask() {
        doNothing().when(taskService).deleteTask(anyInt());

        ResponseEntity<Void> response = taskController.deleteTask(1);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCodeValue()).isEqualTo(204);
        verify(taskService, times(1)).deleteTask(1);
    }
}
