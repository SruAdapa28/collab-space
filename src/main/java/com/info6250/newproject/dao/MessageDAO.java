/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.info6250.newproject.dao;

import com.info6250.newproject.entity.Message;
import com.info6250.newproject.entity.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author srujanaadapa
 */
@Repository
public class MessageDAO {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Transactional
    public List<User> findUniqueSenders(User receiver) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT DISTINCT m.sender FROM Message m WHERE m.receiver = :receiver";
        Query<User> query = session.createQuery(hql, User.class);
        query.setParameter("receiver", receiver);
        return query.getResultList();
    }

    @Transactional
    public List<User> findUniqueRecipients(User sender) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT DISTINCT m.receiver FROM Message m WHERE m.sender = :sender";
        Query<User> query = session.createQuery(hql, User.class);
        query.setParameter("sender", sender);
        return query.getResultList();
    }

    @Transactional
    public List<Message> findChatHistory(User user1, User user2) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Message m WHERE (m.sender = :user1 AND m.receiver = :user2) " +
                     "OR (m.sender = :user2 AND m.receiver = :user1) ORDER BY m.timestamp";
        Query<Message> query = session.createQuery(hql, Message.class);
        query.setParameter("user1", user1);
        query.setParameter("user2", user2);
        return query.getResultList();
    }

    public void save(Message message) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(message);
    }

//    @Transactional
//    public void saveMessage(Message message) {
//        Session session = sessionFactory.openSession();
//        Transaction transaction = null;
//        try {
//            transaction = session.beginTransaction();
//            session.save(message);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null) transaction.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//    }
//
//    @Transactional
//    public List<Message> getMessagesBetweenUsers(User sender, User receiver) {
//        Session session = sessionFactory.openSession();
//        List<Message> messages = null;
//        try {
//            String hql = "FROM Message WHERE (sender = :sender AND receiver = :receiver) OR (sender = :receiver AND receiver = :sender) ORDER BY timestamp";
//            Query<Message> query = session.createQuery(hql, Message.class);
//            query.setParameter("sender", sender);
//            query.setParameter("receiver", receiver);
//            messages = query.list();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return messages;
//    }
//
//    @Transactional
//    public List<Message> findByUsers(User user1, User user2) {
//        String hql = "FROM Message m WHERE (m.sender = :user1 AND m.receiver = :user2) OR (m.sender = :user2 AND m.receiver = :user1) ORDER BY m.timestamp";
//        Query query = sessionFactory.getCurrentSession().createQuery(hql);
//        query.setParameter("user1", user1);
//        query.setParameter("user2", user2);
//        return query.list();
//    }
//    
//    @Transactional
//    public List<Message> findBySender(User sender) {
//        Session session = sessionFactory.getCurrentSession();
//        String hql = "FROM Message m WHERE m.sender = :sender ORDER BY m.timestamp";
//        Query<Message> query = session.createQuery(hql, Message.class);
//        query.setParameter("sender", sender);
//        return query.getResultList();
//    }
//
//    @Transactional
//    public List<Message> findByReceiver(User receiver) {
//        Session session = sessionFactory.getCurrentSession();
//        String hql = "FROM Message m WHERE m.receiver = :receiver ORDER BY m.timestamp";
//        Query<Message> query = session.createQuery(hql, Message.class);
//        query.setParameter("receiver", receiver);
//        return query.getResultList();
//    }
}
