package com.Cynar.taskmanager.repository;
import com.Cynar.taskmanager.model.Task;
import com.Cynar.taskmanager.model.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long>{
    List<Task> findByStatus(TaskStatus status);
    List<Task> findByOwnerId(Long ownerId);
    List<Task> findByProjectID(Long projectId);
    List<Task> findByDueDateBefore(LocalDateTime datetime);
    long countByStatus(TaskStatus status);
    
}
