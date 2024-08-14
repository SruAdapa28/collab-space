/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.info6250.newproject.service;

import com.info6250.newproject.dao.ProjectDAO;
import com.info6250.newproject.entity.Project;
import com.info6250.newproject.entity.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author srujanaadapa
 */
@Service
public class ProjectService {

    @Autowired
    private ProjectDAO projectDAO;

    public List<Project> getAllProjects() {
        return projectDAO.getAllProjects();
    }

    public void saveProject(Project project) {
        projectDAO.save(project);
    }
    
    public Project findById(Integer projectId) {
        return projectDAO.findById(projectId);
    }

    public void updateProject(Project project) {
        projectDAO.saveOrUpdate(project);
    }

    public void deleteProjectById(Integer projectId) {
        projectDAO.deleteById(projectId);
    }
    
    public List<Project> findProjectsByManager(User manager) {
        return projectDAO.findByManagedBy(manager);
    }
}