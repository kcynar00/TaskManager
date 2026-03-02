package com.Cynar.taskmanager.service.impl;

import com.Cynar.taskmanager.exception.TaskNotFoundException;
import com.Cynar.taskmanager.model.Task;
import com.Cynar.taskmanager.model.enums.TaskStatus;
import com.Cynar.taskmanager.repository.TaskRepository;
import com.Cynar.taskmanager.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class TaskServiceImpl implements TaskService {

   private final TaskRepository repository;

   public TaskServiceImpl(TaskRepository repository){
    this.repository = repository;
   }

   @Override
    public Task create(Task task) {

    if (task.getDueDate() != null &&
        task.getDueDate().isBefore(LocalDateTime.now())) {

        throw new IllegalArgumentException("Due date cannot be in the past");
    }

    return repository.save(task);
    }

   @Override
   public Task getById(Long id) {
    return repository.findById(id)
            .orElseThrow(() -> new TaskNotFoundException(id));
   }

   @Override
   public List<Task> getAll() {
    return repository.findAll();
   }

   @Override
   public List<Task> getByStatus(TaskStatus status) {
    return repository.findByStatus(status);
   }

   @Override
   public List<Task> getByOwner(Long ownerId) {
    return repository.findByOwnerId(ownerId);
   }

   @Override
   public List<Task> getByProject(Long projectId) {
    return repository.findByDueDateBefore(LocalDateTime.now());
   }

   @Override
   public List<Task> getOverdueTasks() {
    return repository.findByDueDateBefore(LocalDateTime.now());
   }

   @Override
   public Task updateStatus(Long id, TaskStatus status) {
    Task task = getById(id);
    task.setStatus(status);
    return repository.save(task);
   }

   @Override
   public long countByStatus(TaskStatus status) {
    return repository.countByStatus(status);
   }



}
