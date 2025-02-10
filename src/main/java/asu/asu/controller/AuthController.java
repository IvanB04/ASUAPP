package com.example.datingapp.controller;

import com.example.datingapp.dto.LoginRequest;
import com.example.datingapp.dto.RegisterRequest;
import com.example.datingapp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Renders login.html
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest request, Model model) {
        boolean isAuthenticated = authService.authenticateUser(request);
        if (isAuthenticated) {
            return "redirect:/home"; // Redirect to home page
        }
        model.addAttribute("error", "Invalid credentials");
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register"; // Renders register.html
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterRequest request, Model model) {
        authService.registerUser(request);
        return "redirect:/home"; // Redirect to home page after registration
    }
}
