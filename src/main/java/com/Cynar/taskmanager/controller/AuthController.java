package com.Cynar.taskmanager.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.Cynar.taskmanager.model.User;
import com.Cynar.taskmanager.repository.UserRepository;

@Controller
public class AuthController {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository repository,
                          PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    // =========================
    // STRONA LOGOWANIA
    // =========================
    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    // =========================
    // FORMULARZ REJESTRACJI
    // =========================
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "auth/register";
    }

    // =========================
    // ZAPIS UŻYTKOWNIKA
    // =========================
    @PostMapping("/register")
    public String register(@ModelAttribute User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        repository.save(user);

        return "redirect:/login";
    }
}