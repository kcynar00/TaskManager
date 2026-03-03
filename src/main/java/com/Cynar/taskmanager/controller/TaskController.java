package com.Cynar.taskmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Cynar.taskmanager.model.Task;
import com.Cynar.taskmanager.model.enums.TaskStatus;
import com.Cynar.taskmanager.service.TaskService;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    // =========================
    // LISTA
    // =========================
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("tasks", service.getAll());
        return "tasks/list";
    }

    // =========================
    // FORMULARZ TWORZENIA
    // =========================
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("task", new Task());
        return "tasks/create";
    }

    // =========================
    // ZAPIS
    // =========================
    @PostMapping
    public String create(@ModelAttribute Task task) {
        service.create(task);
        return "redirect:/tasks";
    }

    // =========================
    // SZCZEGÓŁY
    // =========================
    @GetMapping("/{id}")
    public String getById(@PathVariable Long id, Model model) {
        model.addAttribute("task", service.getById(id));
        return "tasks/details";
    }

    // =========================
    // ZMIANA STATUSU
    // =========================
    @PostMapping("/{id}/status")
    public String updateStatus(@PathVariable Long id,
                               @RequestParam TaskStatus status) {
        service.updateStatus(id, status);
        return "redirect:/tasks";
    }

    // =========================
    // FILTR
    // =========================
    @GetMapping("/status/{status}")
    public String getByStatus(@PathVariable TaskStatus status,
                              Model model) {
        model.addAttribute("tasks", service.getByStatus(status));
        return "tasks/list";
    }

    // =========================
    // PRZETERMINOWANE
    // =========================
    @GetMapping("/overdue")
    public String getOverdue(Model model) {
        model.addAttribute("tasks", service.getOverdueTasks());
        return "tasks/list";
    }
}