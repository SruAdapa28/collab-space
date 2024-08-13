/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.info6250.newproject.dao;

import com.info6250.newproject.entity.Project;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author srujanaadapa
 */
@Repository
public class ProjectDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void save(Project project) {
        sessionFactory.getCurrentSession().saveOrUpdate(project);
    }

    @Transactional
    public Project findById(Integer projectId) {
        return sessionFactory.getCurrentSession().get(Project.class, projectId);
    }
    
    @Transactional
    public List<Project> getAllProjects() {
        return sessionFactory.getCurrentSession().createQuery("from Project", Project.class).list();
    }
    
    @Transactional
    public void saveOrUpdate(Project project) {
        sessionFactory.getCurrentSession().saveOrUpdate(project);
    }

    @Transactional
    public void deleteById(Integer projectId) {
        Project project = findById(projectId);
        if (project != null) {
            sessionFactory.getCurrentSession().delete(project);
        }
    }
}