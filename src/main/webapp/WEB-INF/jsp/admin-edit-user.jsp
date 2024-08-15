<%-- 
    Document   : admin-edit-user
    Created on : Aug 13, 2024, 1:33:32 PM
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
    <title>Edit User</title>
</head>
<body>
    <h1>Edit User</h1>
    
    <form action="/newproject/admin/updateUser" method="post">
        <input type="hidden" name="userId" value="${user.id}">
        
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" value="${user.username}" required><br><br>
        
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${user.email}" required><br><br>
        
        <input type="submit" value="Save Changes">
    </form>
    
    <a href="/newproject/admin/dashboard">Back to Dashboard</a>
</body>
</html>
