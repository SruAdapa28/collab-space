<%-- 
    Document   : employee-dashboard
    Created on : Aug 10, 2024, 3:23:18 PM
    Author     : srujanaadapa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Dashboard</title>
    </head>
<body>
    <h1>Welcome, ${username}!</h1>
    
    <h2>Your Projects</h2>
    <ul>
        <c:forEach var="project" items="${projects}">
            <li>
                <strong>Project Name:</strong> ${project.projectName}<br>
                <strong>Description:</strong> ${project.description}<br>
                <strong>Start Date:</strong> ${project.startDate}<br>
                <strong>End Date:</strong> ${project.endDate != null ? project.endDate : 'Ongoing'}<br>
                <strong>Manager:</strong> ${project.managedBy.username}<br>

<!--                <a href="/newproject/developer/viewProject/${project.projectId}">View Tasks</a>-->
            </li>
            <hr>
        </c:forEach>
    </ul>

    <h2>Your Tasks</h2>
    <table border="1">
        <tr>
            <th>Task Name</th>
            <th>Project</th>
            <th>Description</th>
            <th>Due Date</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="task" items="${tasks}">
            <tr>
                <td>${task.taskName}</td>
                <td>${task.project.projectName}</td>
                <td>${task.description}</td>
                <td>${task.dueDate}</td>
                <td>${task.status}</td>
                <td>
                    <a href="/newproject/employee/editTask/${task.taskId}">Edit</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

