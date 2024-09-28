package com.tvm.internal.edit.service;


import com.tvm.internal.edit.model.Tasks;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<Tasks> getAllTasks();
    Optional<Tasks> getTaskById(Integer id);
    Tasks createTask(Tasks task);
    Tasks updateTask(Integer id, Tasks tasks);
    void deleteTask(Integer id);
}
