<%-- 
    Document   : manager-view-project
    Created on : Aug 14, 2024, 12:54:39 PM
    Author     : srujanaadapa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Project Tasks</title>
</head>
<body>
    <h1>Tasks for Project: ${project.projectName}</h1>

    <h2>Existing Tasks</h2>
    <table border="1">
        <tr>
            <th>Task ID</th>
            <th>Task Name</th>
            <th>Description</th>
            <th>Due Date</th>
            <th>Status</th>
            <th>Assigned To</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="task" items="${tasks}">
            <tr>
                <td>${task.taskId}</td>
                <td>${task.taskName}</td>
                <td>${task.description}</td>
                <td>${task.dueDate}</td>
                <td>${task.status}</td>
                <td>${task.assignedTo.username}</td>
                <td>
                    <a href="/newproject/manager/editTask/${task.taskId}">Edit</a> |
                    <a href="/newproject/manager/deleteTask/${task.taskId}" 
                       onclick="return confirm('Are you sure you want to delete this task?');">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <h2>Create New Task</h2>
    <form action="/newproject/manager/addTask" method="post">
        <input type="hidden" name="projectId" value="${project.projectId}">
        
        <label for="taskName">Task Name:</label>
        <input type="text" id="taskName" name="taskName" required><br><br>

        <label for="description">Description:</label>
        <input type="text" id="description" name="description" required><br><br>

        <label for="dueDate">Due Date:</label>
        <input type="date" id="dueDate" name="dueDate" required><br><br>

        <label for="developerUsername">Assign to Developer:</label>
        <select id="developerUsername" name="developerUsername" required>
            <c:forEach var="developer" items="${developers}">
                <option value="${developer.username}">
                    ${developer.username}
                </option>
            </c:forEach>
        </select><br><br>
        
        <label for="status">Task Status:</label>
        <select id="status" name="status" required>
            <c:forEach var="status" items="${taskStatus}">
                <option value="${status}">${status}</option>
            </c:forEach>
        </select><br><br>

        <input type="submit" value="Create Task">
    </form>
    
    <form action="/newproject/manager/dashboard" method="get">
        <input type="submit" value="Back to Dashboard">
    </form>
</body>
</html>
