package com.Cynar.taskmanager.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(
        mappedBy="project",
        cascade=CascadeType.ALL,
        orphanRemoval=true
    )
    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task task){
        tasks.add(task);
        task.setProject(this);
    }
    public void removeTasks(Task task){
        tasks.remove(task);
        task.setProject(null);
    }
    
    public Long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public List<Task> getTasks() {
        return tasks;
    }
}