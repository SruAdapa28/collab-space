/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.info6250.newproject.service;

import com.info6250.newproject.dao.UserDAO;
import com.info6250.newproject.entity.Role;
import com.info6250.newproject.entity.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author srujanaadapa
 */
@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(User user) {
        if (userDAO.findByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getRole() == null) {
            user.setRole(Role.DEVELOPER_CONTRIBUTOR);
        }

        userDAO.save(user);
    }
    
    public boolean authenticateUser(String username, String password) {
        User user = userDAO.findByUsername(username);
        if(user != null && passwordEncoder.matches(password, user.getPassword())){
            return true;
        }

        return user !=null && password.equals(user.getPassword());
    }

    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }
    
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }
    
    public User findById(Long userId) {
        return userDAO.findById(userId);
    }
    
    public void saveUser(User user) {
        userDAO.save(user);
    }
    
    public void updateUser(User user) {
        userDAO.saveOrUpdate(user);  // This will update the existing entity or save if it is new
    }
    
    public void deleteUserById(Long userId) {
        userDAO.deleteById(userId);
    }

    public List<User> findUsersByRole(Role role) {
        return userDAO.findUsersByRole(role);
    }

}