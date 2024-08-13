<%-- 
    Document   : projects
    Created on : Aug 10, 2024, 1:05:31 AM
    Author     : srujanaadapa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin - Manage Projects</title>
</head>
<body>
    <h1>Manage Projects</h1>

    <form action="${pageContext.request.contextPath}/admin/addProject" method="post">
        <h2>Add New Project</h2>
        <label for="projectName">Project Name:</label>
        <input type="text" id="projectName" name="projectName" required /><br />
        <label for="description">Description:</label>
        <textarea id="description" name="description"></textarea><br />
        <label for="startDate">Start Date:</label>
        <input type="date" id="startDate" name="startDate" /><br />
        <label for="endDate">End Date:</label>
        <input type="date" id="endDate" name="endDate" /><br />
        <input type="submit" value="Add Project" />
    </form>

    <c:if test="${not empty successMessage}">
        <p style="color: green;"><c:out value="${successMessage}" /></p>
    </c:if>

    <h2>Existing Projects</h2>
    <table border="1">
        <thead>
            <tr>
                <th>Project Name</th>
                <th>Description</th>
                <th>Start Date</th>
                <th>End Date</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="project" items="${projects}">
                <tr>
                    <td><c:out value="${project.projectName}" /></td>
                    <td><c:out value="${project.description}" /></td>
                    <td><c:out value="${project.startDate}" /></td>
                    <td><c:out value="${project.endDate}" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>

