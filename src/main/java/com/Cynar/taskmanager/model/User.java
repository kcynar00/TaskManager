package com.Cynar.taskmanager.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @OneToMany(
        mappedBy="owner",
        cascade=CascadeType.ALL,
        orphanRemoval=true
    )
    private List<Task> tasks = new ArrayList <>();

    public void addTask(Task task){
        tasks.add(task);
        task.setOwner(this);
    }
    public void removeTask(Task task){
        tasks.remove(task);
        task.setOwner(null);
    }
    public Long getId(){
        return id;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public List<Task> getTasks(){
        return tasks;
    }
}