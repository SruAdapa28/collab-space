/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.info6250.newproject.service;

import com.info6250.newproject.dao.MessageDAO;
import com.info6250.newproject.dao.UserDAO;
import com.info6250.newproject.entity.Message;
import com.info6250.newproject.entity.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author srujanaadapa
 */
@Service
public class MessageService {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    private MessageDAO messageDAO;
    
    @Autowired
    private UserDAO userDAO;

    @Transactional
    public List<User> getInteractedUsers(User currentUser) {
        // Find users that the current user has either sent to or received messages from
        List<User> senders = messageDAO.findUniqueSenders(currentUser);
        List<User> recipients = messageDAO.findUniqueRecipients(currentUser);
        
        // Combine the lists and remove duplicates
        Set<User> interactedUsers = new HashSet<>(senders);
        interactedUsers.addAll(recipients);
        
        return new ArrayList<>(interactedUsers);
    }

    @Transactional
    public List<Message> getChatHistory(User currentUser, User otherUser) {
        return messageDAO.findChatHistory(currentUser, otherUser);
    }

    @Transactional
    public void sendMessage(User sender, User recipient, String messageText) {
        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(recipient);
        message.setContent(messageText);
        message.setTimestamp(new java.sql.Timestamp(System.currentTimeMillis()));
        
        messageDAO.save(message);
    }

//    @Transactional
//    public void saveMessage(Message message) {
//        try (Session session = sessionFactory.openSession()) {
//            Transaction transaction = session.beginTransaction();
//            session.save(message);
//            transaction.commit();
//        }
//    }
//
//    @Transactional
//    public List<Message> getMessagesByChat(Chat chat) {
//        try (Session session = sessionFactory.openSession()) {
//            Query<Message> query = session.createQuery("FROM Message WHERE chat = :chat ORDER BY timestamp", Message.class);
//            query.setParameter("chat", chat);
//            return query.list();
//        }
//    }
//
//    @Transactional
//    public List<Chat> getChatsByUser(User user) {
//        try (Session session = sessionFactory.openSession()) {
//            Query<Chat> query = session.createQuery("FROM Chat WHERE user1 = :user OR user2 = :user ORDER BY lastUpdated DESC", Chat.class);
//            query.setParameter("user", user);
//            return query.list();
//        }
//    }
//    
//    public List<User> getInteractedUsers(User currentUser) {
//
//        List<Message> sentMessages = messageDAO.findBySender(currentUser);
//        List<Message> receivedMessages = messageDAO.findByReceiver(currentUser);
//
//        Set<User> interactedUsers = new HashSet<>();
//
//        // Add all users who the current user sent messages to
//        for (Message message : sentMessages) {
//            interactedUsers.add(message.getReceiver());
//        }
//
//        // Add all users who sent messages to the current user
//        for (Message message : receivedMessages) {
//            interactedUsers.add(message.getSender());
//        }
//
//        return new ArrayList<>(interactedUsers);
//    }
//    
//    public List<Message> getChatHistory(User user1, User user2) {
//        return messageDAO.findByUsers(user1, user2);
//    }
}
