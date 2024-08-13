/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.info6250.newproject.controller;

import com.info6250.newproject.entity.Project;
import com.info6250.newproject.entity.Role;
import com.info6250.newproject.entity.User;
import com.info6250.newproject.service.ProjectService;
import com.info6250.newproject.service.UserService;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author srujanaadapa
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private UserService userService;    

    @Autowired
    private ProjectService projectService;
    
    @GetMapping("/dashboard")
    public String showAdminDashboard(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        
        List<User> users = userService.getAllUsers();
        List<Project> projects = projectService.getAllProjects();
        List<User> managers = userService.findUsersByRole(Role.PROJECT_MANAGER);
        
        model.addAttribute("username", username);
        model.addAttribute("users", users);
        model.addAttribute("projects", projects);
        model.addAttribute("managers", managers);

        return "admin-dashboard";
    }

    @GetMapping("/projects")
    public String showProjectsPage(Model model) {
        model.addAttribute("projects", projectService.getAllProjects());
        model.addAttribute("newProject", new Project());
        return "projects";
    }

    @PostMapping("/addProject")
    public String addProject(@RequestParam String projectName,
                             @RequestParam String description,
                             @RequestParam String startDate,
                             @RequestParam(required = false) String endDate,
                             @RequestParam String createdByUsername,
                             @RequestParam String managerUsername, 
                             RedirectAttributes redirectAttributes, Model model) {
        Project project = new Project();
        project.setProjectName(projectName);
        project.setDescription(description);
        LocalDate localStartDate = LocalDate.parse(startDate);
        Date startD = Date.valueOf(localStartDate);
        project.setStartDate(startD);
        
        User user = userService.findByUsername(createdByUsername);
        project.setCreatedBy(user);
        
        User manager = userService.findByUsername(managerUsername);  
        project.setManagedBy(manager); 
        
        if (endDate != null && !endDate.isEmpty()) {
            LocalDate localEndDate = LocalDate.parse(endDate);
            Date endD = Date.valueOf(localEndDate);
            project.setEndDate(endD);
        }

        projectService.saveProject(project);
        
        redirectAttributes.addFlashAttribute("username", createdByUsername);
        return "redirect:/admin/dashboard";
    }
    
    @GetMapping("/editUser/{userId}")
    public String showEditUserPage(@PathVariable("userId") Long userId, Model model) {
        User user = userService.findById(userId);
        model.addAttribute("user", user);
        return "admin-edit-user";
    }
    
    @PostMapping("/updateUser")
    public String updateUser(@RequestParam Long userId,
                             @RequestParam String username,
                             @RequestParam String email,
                             RedirectAttributes redirectAttributes) {
        User user = userService.findById(userId);
        user.setUsername(username);
        user.setEmail(email);
        userService.updateUser(user);

        redirectAttributes.addFlashAttribute("successMessage", "User updated successfully.");
        return "redirect:/admin/dashboard";
    }
    
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long userId, RedirectAttributes redirectAttributes) {
        //need a check if the user is assigned to any project/task -- cannot delete
        userService.deleteUserById(userId);
        redirectAttributes.addFlashAttribute("successMessage", "User deleted successfully.");
        return "redirect:/admin/dashboard";
    }
    
    @GetMapping("/editProject/{id}")
    public String showEditProjectForm(@PathVariable("id") Integer projectId,
                                        HttpSession session,
                                        Model model) {
        String username = (String) session.getAttribute("username");
        Project project = projectService.findById(projectId);
        List<User> managers = userService.findUsersByRole(Role.PROJECT_MANAGER);
        
        model.addAttribute("project", project);
        model.addAttribute("managers", managers);
        model.addAttribute("username", username);
        return "admin-edit-project";
    }
    
 @PostMapping("/editProject")
    public String editProject(@RequestParam Integer projectId,
                              @RequestParam String projectName,
                              @RequestParam String description,
                              @RequestParam String startDate,
                              @RequestParam(required = false) String endDate,
                              @RequestParam String managerName,
                              @RequestParam String username,
                              RedirectAttributes redirectAttributes) {

        Project project = projectService.findById(projectId);
        project.setProjectName(projectName);
        project.setDescription(description);
        project.setStartDate(Date.valueOf(LocalDate.parse(startDate)));

        if (endDate != null && !endDate.isEmpty()) {
            project.setEndDate(Date.valueOf(LocalDate.parse(endDate)));
        }

        User manager = userService.findByUsername(managerName);
        project.setManagedBy(manager);

        projectService.updateProject(project);

        redirectAttributes.addFlashAttribute("successMessage", "Project updated successfully.");
        redirectAttributes.addFlashAttribute("username", username);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/deleteProject/{id}")
    public String deleteProject(@PathVariable("id") Integer projectId,  HttpSession session, RedirectAttributes redirectAttributes) {
        String username = (String) session.getAttribute("username");
        projectService.deleteProjectById(projectId);
        redirectAttributes.addFlashAttribute("successMessage", "Project deleted successfully.");
        return "redirect:/admin/dashboard";
    }
}
