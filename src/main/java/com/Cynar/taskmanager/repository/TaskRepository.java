package com.Cynar.taskmanager.repository;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Cynar.taskmanager.model.Task;
import com.Cynar.taskmanager.model.enums.TaskStatus;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long>{
    List<Task> findByStatus(TaskStatus status);
    List<Task> findByOwnerId(Long ownerId);
    List<Task> findByProjectId(Long projectId);
    List<Task> findByDueDateBefore(LocalDateTime datetime);
    long countByStatus(TaskStatus status);
    
}
