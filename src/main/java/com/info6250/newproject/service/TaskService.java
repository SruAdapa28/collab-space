/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.info6250.newproject.service;

import com.info6250.newproject.dao.TaskDAO;
import com.info6250.newproject.entity.Project;
import com.info6250.newproject.entity.Task;
import com.info6250.newproject.entity.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author srujanaadapa
 */
@Service
public class TaskService {
    
    @Autowired
    private TaskDAO taskDAO;
    
    public Task findById(Integer taskId) {
        return taskDAO.findById(taskId);
    }
    
    public List<Task> findTasksByProject(Project project) {
        return taskDAO.findByProject(project);
    }
    
    public void saveTask(Task task) {
        taskDAO.save(task);
    }
    
    public void updateTask(Task task) {
        taskDAO.saveOrUpdate(task);
    }
    
    public void deleteTask(Integer taskId) {
        taskDAO.deleteById(taskId);
    }
    
    public List<Task> findTasksByAssignedTo(User employee) {
        return taskDAO.findByAssignedTo(employee);
    }
}
