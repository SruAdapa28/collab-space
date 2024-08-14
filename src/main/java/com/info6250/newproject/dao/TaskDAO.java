/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.info6250.newproject.dao;

import com.info6250.newproject.entity.Project;
import com.info6250.newproject.entity.Task;
import com.info6250.newproject.entity.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author srujanaadapa
 */

@Repository
public class TaskDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void save(Task task) {
        sessionFactory.getCurrentSession().save(task);
    }

    @Transactional
    public Task findById(Integer taskId) {
        return sessionFactory.getCurrentSession().get(Task.class, taskId);
    }
    
    @Transactional
    public List<Task> findByProject(Project project) {
        // Get the current Hibernate session
        Session session = sessionFactory.getCurrentSession();

        // Create the query to fetch tasks by project
        Query<Task> query = session.createQuery("from Task where project = :project", Task.class);
        query.setParameter("project", project);

        // Execute the query and get the results
        return query.getResultList();
        
        
    }
    
    @Transactional
    public void saveOrUpdate(Task task){
        sessionFactory.getCurrentSession().saveOrUpdate(task);
    }
    
    @Transactional
    public void deleteById(Integer taskId) {
        Task task = findById(taskId);
        if (task != null) {
            sessionFactory.getCurrentSession().delete(task);
        }
    }
    
    @Transactional
    public List<Task> findByAssignedTo(User employee) {

        Session session = sessionFactory.getCurrentSession();

        Query<Task> query = session.createQuery("from Task where assignedTo = :employee", Task.class);
        query.setParameter("employee", employee);

        return query.getResultList();
    }

}
