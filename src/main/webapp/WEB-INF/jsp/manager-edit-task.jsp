<%-- 
    Document   : manager-edit-task
    Created on : Aug 14, 2024, 5:45:23 PM
    Author     : srujanaadapa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit Task</title>
</head>
<body>
    <h2>Edit Task</h2>
    <form action="/newproject/manager/editTask" method="post">
        <input type="hidden" name="taskId" value="${task.taskId}">
        <input type="hidden" name="projectId" value="${task.project.projectId}">
        
        <label for="taskName">Task Name:</label>
        <input type="text" id="taskName" name="taskName" value="${task.taskName}" required><br><br>

        <label for="description">Description:</label>
        <input type="text" id="description" name="description" value="${task.description}"><br><br>

        <label for="dueDate">Due Date:</label>
        <input type="date" id="dueDate" name="dueDate" value="${task.dueDate}"><br><br>

        <label for="developerUsername">Assign to Developer:</label>
        <select id="developerUsername" name="developerUsername" required>
            <c:forEach var="developer" items="${developers}">
                <option value="${developer.username}" ${task.assignedTo.username == developer.username ? 'selected' : ''}>
                    ${developer.username}
                </option>
            </c:forEach>
        </select><br><br>

        <label for="status">Task Status:</label>
        <select id="status" name="status" required>
            <c:forEach var="status" items="${TaskStatus}">
                <option value="${status}" ${task.status == status ? 'selected' : ''}>${status}</option>
            </c:forEach>
        </select><br><br>

        <input type="submit" value="Update Task">
    </form>
    
    <form action="/newproject/manager/dashboard" method="get">
        <input type="submit" value="Back to Dashboard">
    </form>
</body>
</html>

