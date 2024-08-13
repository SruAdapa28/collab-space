/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.info6250.newproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author srujanaadapa
 */
@Controller
@RequestMapping("/manager")
public class ManagerController {

    @GetMapping("/dashboard")
    public String showManagerDashboard(@ModelAttribute("username") String username, Model model) {
        model.addAttribute("username", username);
        return "manager-dashboard"; 
    }
}

