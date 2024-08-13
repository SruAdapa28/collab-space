/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.info6250.newproject.dao;

import com.info6250.newproject.entity.Role;
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
public class UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User", User.class).list();
    }
    
    @Transactional
    public User findByUsername(String username) {
        return sessionFactory.getCurrentSession()
                             .createQuery("FROM User WHERE username = :username", User.class)
                             .setParameter("username", username)
                             .uniqueResult();
    }

    @Transactional
    public void save(User user) {
        sessionFactory.getCurrentSession().save(user);
    }
    
    

    @Transactional
    public User findById(Long userId) {
        return sessionFactory.getCurrentSession()
                             .createQuery("FROM User WHERE id = :userId", User.class)
                             .setParameter("userId", userId)
                             .uniqueResult();    
    }
    
    @Transactional
    public void saveOrUpdate(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }
    
    @Transactional
    public void deleteById(Long userId) {
        User user = sessionFactory.getCurrentSession().get(User.class, userId);
        if (user != null) {
            sessionFactory.getCurrentSession().delete(user);
        }
    }
    
    @Transactional
    public List<User> findUsersByRole(Role role) {
        String hql = "FROM User u WHERE u.role = :role";
        Query<User> query = sessionFactory.getCurrentSession().createQuery(hql, User.class);
        query.setParameter("role", role);
        return query.list();
    }
}