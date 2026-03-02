package com.Cynar.taskmanager.service;

import com.Cynar.taskmanager.model.Task;
import com.Cynar.taskmanager.model.enums.TaskStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskService {

    Task create(Task task);

    Task getById(Long id);

    List<Task> getAll();

    List<Task> getByStatus(TaskStatus status);

    List<Task> getByOwner(Long ownerId);

    List<Task> getByProject(Long projectId);

    List<Task> getOverdueTasks();

    Task updateStatus(Long id, TaskStatus status);

    long countByStatus(TaskStatus status);
    
}
