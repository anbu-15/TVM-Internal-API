package com.tvm.internal.edit.service;

import com.tvm.internal.edit.model.Tasks;
import com.tvm.internal.edit.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    public List<Tasks> getAllTasks() {
        return taskRepo.findAll();
    }

    public Optional<Tasks> getTaskById(Integer id) {
        return taskRepo.findById(id);
    }

    public Tasks createTask(Tasks task) {
        return taskRepo.save(task);
    }

    public Tasks updateTask(Integer id, Tasks tasks) {
        Tasks task = taskRepo.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTaskOwner(tasks.getTaskOwner());
        task.setTaskName(tasks.getTaskName());
        task.setDescription(tasks.getDescription());
        task.setPriority(tasks.getPriority());
        task.setStatus(tasks.getStatus());
        task.setStartDate(tasks.getStartDate());
        task.setDueDate(tasks.getDueDate());
        task.setReminder(tasks.getReminder());
        return taskRepo.save(task);
    }

    public void deleteTask(Integer id) {
        taskRepo.deleteById(id);
    }
}