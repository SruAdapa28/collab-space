<%-- 
    Document   : employee-edit-task
    Created on : Aug 14, 2024, 6:45:49 PM
    Author     : srujanaadapa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Expires" content="0">
        <title>Edit Task</title>
    </head>
<body>
    <h1>Edit Task</h1>

    <form action="/newproject/employee/editTask" method="post">
        <input type="hidden" name="taskId" value="${task.taskId}">

        <label for="taskName">Task Name:</label>
        <input type="text" id="taskName" name="taskName" value="${task.taskName}" readonly><br><br>

        <label for="projectName">Project Name:</label>
        <input type="text" id="projectName" name="projectName" value="${task.project.projectName}" readonly><br><br>

        <label for="assignedTo">Assigned To:</label>
        <input type="text" id="assignedTo" name="assignedTo" value="${task.assignedTo.username}" readonly><br><br>

        <label for="dueDate">Due Date:</label>
        <input type="text" id="dueDate" name="dueDate" value="${task.dueDate}" readonly><br><br>

        <label for="description">Description:</label>
        <textarea id="description" name="description" required>${task.description}</textarea><br><br>

        <label for="status">Status:</label>
        <select id="status" name="status" required>
            <c:forEach var="status" items="${statuses}">
                <option value="${status}" ${status == task.status ? 'selected' : ''}>${status}</option>
            </c:forEach>
        </select><br><br>

        <input type="submit" value="Save">
    </form>

    <form action="/newproject/employee/dashboard" method="get">
        <input type="submit" value="Back to Dashboard">
    </form>
</body>
</html>
