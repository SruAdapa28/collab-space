<%-- 
    Document   : register
    Created on : Aug 9, 2024, 1:02:22 PM
    Author     : srujanaadapa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Registration</title>
</head>
<body>
    <h2>Register</h2>
    <form action="${pageContext.request.contextPath}/api/auth/register" method="post">
        <label for="username">Username:</label>
        <input type="text" name="username" id="username" required/><br><br>
        
        <label for="password">Password:</label>
        <input type="password" name="password" id="password" required/><br><br>
        
        <label for="email">Email:</label>
        <input type="email" name="email" id="email" required/><br><br>
        
        <label for="role">Role:</label>
        <select name="role" id="role">
            <option value="DEVELOPER_CONTRIBUTOR">Developer/Contributor</option>
            <option value="PROJECT_MANAGER">Project Manager</option>
            <option value="ADMIN">Admin</option>
        </select><br><br>
        
        <input type="submit" value="Register"/>
    </form>
    <c:if test="${not empty errorMessage}">
        <div style="color:red;">${errorMessage}</div>
    </c:if>
    <c:if test="${not empty successMessage}">
        <div style="color:green;">${successMessage}</div>
    </c:if>
</body>
</html>