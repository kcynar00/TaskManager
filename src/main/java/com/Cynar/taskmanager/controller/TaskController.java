package com.Cynar.taskmanager.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Cynar.taskmanager.model.Task;
import com.Cynar.taskmanager.model.enums.TaskStatus;
import com.Cynar.taskmanager.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service){
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task){
        Task created = service.create(task);
        return ResponseEntity.ok(created);
    }
    @GetMapping
    public ResponseEntity<List <Task>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.getById(id));
    }
    @GetMapping("/status/{status}")
    public ResponseEntity<List <Task>> getByStatus(@PathVariable TaskStatus status){
        return ResponseEntity.ok(service.getByStatus(status));
    }
    @PatchMapping("/{id}/status")
    public ResponseEntity<Task> updateStatus(
        @PathVariable Long id,
        @RequestParam TaskStatus status) {
        return ResponseEntity.ok(service.updateStatus(id, status));
    }
    @GetMapping("/overdue")
    public ResponseEntity<List<Task>> getOverdue(){
        return ResponseEntity.ok(service.getOverdueTasks());
    }
    
}
