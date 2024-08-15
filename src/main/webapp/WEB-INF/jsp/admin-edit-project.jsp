<%-- 
    Document   : admin-edit-project
    Created on : Aug 13, 2024, 4:56:47 PM
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
        <title>Edit Project</title>
    </head>
<body>
    <h1>Edit Project</h1>

    <form action="/newproject/admin/editProject" method="post">
        <input type="hidden" name="projectId" value="${project.projectId}">
        <input type="hidden" name="username" value="${username}">
        
        <label for="projectName">Project Name:</label>
        <input type="text" id="projectName" name="projectName" value="${project.projectName}" required><br><br>

        <label for="description">Description:</label>
        <input type="text" id="description" name="description" value="${project.description}" required><br><br>

        <label for="startDate">Start Date:</label>
        <input type="date" id="startDate" name="startDate" value="${project.startDate}" required><br><br>

        <label for="endDate">End Date:</label>
        <input type="date" id="endDate" name="endDate" value="${project.endDate}"><br><br>
            
            
        
        <label for="managerId">Assign to Manager:</label>
        <select id="managerName" name="managerName" required>
            <c:forEach var="manager" items="${managers}">
                <option value="${manager.username}" ${project.managedBy != null && project.managedBy.username == manager.username ? 'selected' : ''}>
                    ${manager.username}
                </option>
            </c:forEach>
        </select><br><br>

        <input type="submit" value="Save">
    </form>

    <form action="/newproject/admin/dashboard" method="get">
        <input type="submit" value="Back to Dashboard">
    </form>
</body>
</html>
