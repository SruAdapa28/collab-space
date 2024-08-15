/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.info6250.newproject.controller;

import com.info6250.newproject.entity.Role;
import com.info6250.newproject.entity.User;
import com.info6250.newproject.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author srujanaadapa
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/register")
    public String showRegistrationPage(HttpSession session) {
        session.invalidate(); 
        return "register";
    }

    @GetMapping("/")
    public String redirectToRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        try {
            userService.registerUser(user);
            model.addAttribute("successMessage", "User registered successfully.");
            return "register";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "register";
        }
    }
    
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    
    @PostMapping("/login")
    public String loginUser(@RequestParam(name = "username") String username, 
                            @RequestParam(name = "password") String password, 
                            Model model, HttpSession session) {
    
        boolean isAuthenticated = userService.authenticateUser(username, password);
    
        if (isAuthenticated) {
                User user = userService.findByUsername(username);
                Role role = user.getRole();

//                redirectAttributes.addFlashAttribute("username", username);
            session.setAttribute("username", username);

            if (null != role) // Redirect based on user role
                switch (role) {
                    case ADMIN:
                        return "redirect:/admin/dashboard"; // Admin Dashboard
                    case PROJECT_MANAGER:
                        return "redirect:/manager/dashboard"; // Manager Dashboard
                    case DEVELOPER_CONTRIBUTOR:
                        return "redirect:/employee/dashboard"; // Employee Dashboard
                    default:
                        break;
                }
        } else {
            model.addAttribute("errorMessage", "Invalid username or password.");
            return "login";
        }
        
        return "login"; 
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); 
        return "redirect:/auth/login";
    }
}