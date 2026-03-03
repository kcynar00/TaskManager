package com.Cynar.taskmanager.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    @JsonIgnore
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