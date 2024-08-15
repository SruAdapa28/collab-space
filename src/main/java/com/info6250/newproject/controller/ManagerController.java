/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.info6250.newproject.controller;

import com.info6250.newproject.entity.Message;
import com.info6250.newproject.entity.Project;
import com.info6250.newproject.entity.Role;
import com.info6250.newproject.entity.Task;
import com.info6250.newproject.entity.TaskStatus;
import com.info6250.newproject.entity.User;
import com.info6250.newproject.service.MessageService;
import com.info6250.newproject.service.ProjectService;
import com.info6250.newproject.service.TaskService;
import com.info6250.newproject.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
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
@RequestMapping("/manager")
public class ManagerController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ProjectService projectService;
    
    @Autowired
    private TaskService taskService;
    
    @Autowired
    private MessageService messageService;

    @GetMapping("/dashboard")
    public String showManagerDashboard(HttpSession session, HttpServletResponse response, Model model) {
        String username = (String) session.getAttribute("username");
        
        if(username==null){
            model.addAttribute("errorMessage", "Page does not exist");
            return "error";
        }
        
        // Prevent caching of this page
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        
        User manager = userService.findByUsername(username);

        List<Project> projects = projectService.findProjectsByManager(manager);
        model.addAttribute("projects", projects);
        model.addAttribute("username", username);

        return "manager-dashboard";
    }
    
    @GetMapping("/viewProject/{projectId}")
    public String viewProjectTasks(@PathVariable("projectId") Integer projectId,
                                    Model model, HttpSession session, HttpServletResponse response) {
        
        String username = (String) session.getAttribute("username");
        
        if(username==null){
            model.addAttribute("errorMessage", "Page does not exist");
            return "error";
        }
        
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        
        Project project = projectService.findById(projectId);
        List<Task> tasks = (List<Task>) taskService.findTasksByProject(project);
        List<User> developers = userService.findUsersByRole(Role.DEVELOPER_CONTRIBUTOR);


        model.addAttribute("project", project);
        model.addAttribute("tasks", tasks);
        model.addAttribute("developers", developers);
        model.addAttribute("taskStatus", TaskStatus.values());
        
        model.addAttribute("username", username);

        return "manager-view-project";
    }
    
    @PostMapping("/addTask")
    public String addTask(@RequestParam Integer projectId,
                          @RequestParam String taskName,
                          @RequestParam String description,
                          @RequestParam String dueDate,
                          @RequestParam String developerUsername,
                          @RequestParam String status,
                          HttpSession session,
                          RedirectAttributes redirectAttributes) {
        Project project = projectService.findById(projectId);

        Task task = new Task();
        task.setTaskName(taskName);
        task.setDescription(description);
        task.setProject(project);

        task.setStatus(TaskStatus.valueOf(status));
        
        LocalDate localDueDate = LocalDate.parse(dueDate);
        task.setDueDate(Date.valueOf(localDueDate));

        User assignedTo = userService.findByUsername(developerUsername);
        task.setAssignedTo(assignedTo);

        taskService.saveTask(task);

        String username = (String) session.getAttribute("username");
        redirectAttributes.addFlashAttribute("username", username);

        return "redirect:/manager/viewProject/" + projectId;
    }
    
    @GetMapping("/editTask/{taskId}")
    public String showEditTaskForm(@PathVariable("taskId") Integer taskId, Model model, 
            HttpSession session, HttpServletResponse response) {
        
        String username = (String) session.getAttribute("username");
        
        if(username==null){
            model.addAttribute("errorMessage", "Page does not exist");
            return "error";
        }
        
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        
        Task task = taskService.findById(taskId);
        List<User> developers = userService.findUsersByRole(Role.DEVELOPER_CONTRIBUTOR);

        model.addAttribute("task", task);
        model.addAttribute("developers", developers);
        model.addAttribute("TaskStatus", TaskStatus.values());

        return "manager-edit-task";
    }

    @PostMapping("/editTask")
    public String editTask(@RequestParam Integer taskId,
                           @RequestParam String taskName,
                           @RequestParam String description,
                           @RequestParam String dueDate,
                           @RequestParam String developerUsername,
                           @RequestParam String status,
                           @RequestParam Integer projectId,
                           RedirectAttributes redirectAttributes) {

        Task task = taskService.findById(taskId);
        task.setTaskName(taskName);
        task.setDescription(description);
        task.setDueDate(Date.valueOf(LocalDate.parse(dueDate)));

        User developer = userService.findByUsername(developerUsername);
        task.setAssignedTo(developer);

        task.setStatus(TaskStatus.valueOf(status));

        taskService.updateTask(task);

        redirectAttributes.addFlashAttribute("message", "Task updated successfully");

        return "redirect:/manager/viewProject/" + projectId;
    }
    
    @GetMapping("/deleteTask/{taskId}")
    public String deleteTask(@PathVariable("taskId") Integer taskId, RedirectAttributes redirectAttributes) {
        Task task = taskService.findById(taskId);
        Integer projectId = task.getProject().getProjectId();

        taskService.deleteTask(taskId);

        redirectAttributes.addFlashAttribute("message", "Task deleted successfully");

        return "redirect:/manager/viewProject/" + projectId;
    }
    
}

