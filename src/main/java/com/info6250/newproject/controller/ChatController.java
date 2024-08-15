/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.info6250.newproject.controller;

import com.info6250.newproject.entity.Message;
import com.info6250.newproject.entity.Project;
import com.info6250.newproject.entity.Role;
import com.info6250.newproject.entity.User;
import com.info6250.newproject.service.MessageService;
import com.info6250.newproject.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author srujanaadapa
 */
@Controller
@RequestMapping("/chat")
public class ChatController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @GetMapping("/dashboard")
    public String showManagerDashboard(HttpSession session, HttpServletResponse response, Model model) {
        String username = (String) session.getAttribute("username");
        
        if(username==null){
            model.addAttribute("errorMessage", "Page does not exist");
            return "error";
        }
        
        User currentUser = userService.findByUsername(username);
        
        
        if(currentUser.getRole()== Role.PROJECT_MANAGER){
            return "redirect:/manager/dashboard";
        }else if(currentUser.getRole()== Role.DEVELOPER_CONTRIBUTOR){
            return "redirect:/employee/dashboard";
        }

        return "redirect:/auth/login";
    }
    

    @GetMapping("/user-chat")
    public String showUserChat(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if(username==null){
            model.addAttribute("errorMessage", "Page does not exist");
            return "error";
        }
        User sender = userService.findByUsername(username);
        List<User> allUsers = userService.getAllUsers();
        
        List<User> filteredUsers = allUsers.stream()
            .filter(user -> !user.getUsername().equals(sender.getUsername()) && user.getRole() != Role.ADMIN)
            .collect(Collectors.toList());

        model.addAttribute("users", filteredUsers);
        return "user-chat";
    }

    @GetMapping("/chat-box/{userId}")
    public String showChatBox(@PathVariable("userId") Long userId, Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if(username==null){
            model.addAttribute("errorMessage", "Page does not exist");
            return "error";
        }
        User sender = userService.findByUsername(username);
        User selectedUser = userService.findById(userId);
        List<Message> chatHistory = messageService.getChatHistory(sender, selectedUser);
        model.addAttribute("selectedUser", selectedUser);
        model.addAttribute("chatHistory", chatHistory);
        return "chat-box";
    }

    @PostMapping("/sendMessage")
    public String sendMessage(@RequestParam("recipientId") Long recipientId,
                              @RequestParam("messageText") String messageText,
                              HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if(username==null){
            model.addAttribute("errorMessage", "Page does not exist");
            return "error";
        }
        User sender = userService.findByUsername(username);
        User recipient = userService.findById(recipientId);
        
        if (sender != null && recipient != null) {
            messageService.sendMessage(sender, recipient, messageText);
        } else {
            return "redirect:/chat/user-chat";
        }

        return "redirect:/chat/chat-box/" + recipientId;
    }
    

}
