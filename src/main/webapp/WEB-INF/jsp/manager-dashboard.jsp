<%-- 
    Document   : manager-dashboard
    Created on : Aug 10, 2024, 3:22:43 PM
    Author     : srujanaadapa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manager Dashboard</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Expires" content="0">
</head>
<body>
    <h1>Welcome, ${username}!</h1>

    <h2>Your Projects</h2>
    <table border="1">
        <tr>
            <th>Project ID</th>
            <th>Project Name</th>
            <th>Description</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="project" items="${projects}">
            <tr>
                <td>${project.projectId}</td>
                <td>${project.projectName}</td>
                <td>${project.description}</td>
                <td>
                    <a href="/newproject/manager/viewProject/${project.projectId}">View Tasks</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    
    <h3>Messages</h3>
    <a href="/newproject/chat/user-chat">Go to Chat</a>
    
    <a href="/newproject/auth/logout">Logout</a>
</body>
</html>