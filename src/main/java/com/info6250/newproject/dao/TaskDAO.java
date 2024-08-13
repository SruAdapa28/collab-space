/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.info6250.newproject.dao;

import com.info6250.newproject.entity.Task;
import org.hibernate.SessionFactory;
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
}
