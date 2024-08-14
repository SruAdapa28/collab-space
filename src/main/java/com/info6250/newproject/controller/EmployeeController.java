/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.info6250.newproject.controller;

import com.info6250.newproject.entity.Project;
import com.info6250.newproject.entity.Task;
import com.info6250.newproject.entity.TaskStatus;
import com.info6250.newproject.entity.User;
import com.info6250.newproject.service.ProjectService;
import com.info6250.newproject.service.TaskService;
import com.info6250.newproject.service.UserService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author srujanaadapa
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private TaskService taskService;
    
    @Autowired
    private ProjectService projectService;

    @GetMapping("/dashboard")
    public String showEmployeeDashboard(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        User employee = userService.findByUsername(username);

        List<Task> tasks = taskService.findTasksByAssignedTo(employee);
        Set<Project> projects = tasks.stream().map(Task::getProject).collect(Collectors.toSet());

        model.addAttribute("tasks", tasks);
        model.addAttribute("projects", projects);

        return "employee-dashboard";
    }
    
    @GetMapping("/editTask/{taskId}")
    public String showEditTaskForm(@PathVariable("taskId") Integer taskId, Model model) {
        Task task = taskService.findById(taskId);
        model.addAttribute("task", task);
        model.addAttribute("statuses", TaskStatus.values());
        return "employee-edit-task";
    }

    @PostMapping("/editTask")
    public String editTask(@RequestParam Integer taskId,
                           @RequestParam String description,
                           @RequestParam TaskStatus status) {
        Task task = taskService.findById(taskId);
        task.setDescription(description);
        task.setStatus(status);
        taskService.updateTask(task);
        return "redirect:/employee/dashboard";
    }
    
    @GetMapping("/viewProject/{projectId}")
    public String viewProjectTasks(@PathVariable("projectId") Integer projectId, Model model) {
        Project project = projectService.findById(projectId);
        List<Task> tasks = taskService.findTasksByProject(project);

        model.addAttribute("project", project);
        model.addAttribute("tasks", tasks);

        return "developer-view-tasks";
    }
}
