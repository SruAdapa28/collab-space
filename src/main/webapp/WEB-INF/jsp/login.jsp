<%-- 
    Document   : login
    Created on : Aug 9, 2024, 1:02:37 PM
    Author     : srujanaadapa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Login</title>
</head>
<body>
    <h2>Login</h2>
    <form action="${pageContext.request.contextPath}/auth/login" method="post">
        <label for="username">Username:</label>
        <input type="text" name="username" id="username" required/><br><br>
        
        <label for="password">Password:</label>
        <input type="password" name="password" id="password" required/><br><br>
        
        <input type="submit" value="Login"/>
    </form>
    <c:if test="${not empty errorMessage}">
        <div style="color:red;">${errorMessage}</div>
    </c:if>
</body>
</html>
