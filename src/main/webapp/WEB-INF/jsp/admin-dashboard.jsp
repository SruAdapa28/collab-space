<%-- 
    Document   : admin-dashboard
    Created on : Aug 10, 2024, 3:21:39 PM
    Author     : srujanaadapa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Admin Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Expires" content="0">
    </head>
<body>
    <h1>Welcome, ${username}!</h1>
    
    <h2>All Users</h2>
    <table border="1">
        <tr>
            <th>User ID</th>
            <th>Username</th>
            <th>Email</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>
                    <!-- Add actions like Edit/Delete as needed -->
                    <a href="/newproject/admin/editUser/${user.id}">Edit</a> | 
                    <a href="/newproject/admin/deleteUser/${user.id}" 
                       onclick="return confirm('Are you sure you want to delete this user?');">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    
    <h2>All Projects</h2>
    <table border="1">
        <tr>
            <th>Project ID</th>
            <th>Project Name</th>
            <th>Description</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="project" items="${projects}">
            <tr>
                <td>${project.projectId}</td>
                <td>${project.projectName}</td>
                <td>${project.description}</td>
                <td>${project.startDate}</td>
                <td>${project.endDate}</td>
                <td>
                    <a href="/newproject/admin/editProject/${project.projectId}">Edit</a> | 
                    <a href="/newproject/admin/deleteProject/${project.projectId}" 
                       onclick="return confirm('Are you sure you want to delete this project?');">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <h2>Add New Project</h2>
    <form action="/newproject/admin/addProject" method="post">
        <label for="projectName">Project Name:</label>
        <input type="text" id="projectName" name="projectName" required><br><br>
        <label for="description">Description:</label>
        <input type="text" id="description" name="description" required><br><br>
        <label for="startDate">Start Date:</label>
        <input type="date" id="startDate" name="startDate" required><br><br>
        <label for="endDate">End Date:</label>
        <input type="date" id="endDate" name="endDate"><br><br>
        <label for="manager">Project Manager:</label>
        <select id="manager" name="managerUsername" required>
            <option value="" disabled selected>Select a manager</option>
            <c:forEach var="user" items="${managers}">
                <option value="${user.username}">${user.username}</option>
            </c:forEach>
        </select><br><br>
        <input type="hidden" name="createdByUsername" value="${username}">
        <input type="submit" value="Add Project">
    </form>
        
     <a href="/newproject/auth/logout">Logout</a>
</body>
</html>
