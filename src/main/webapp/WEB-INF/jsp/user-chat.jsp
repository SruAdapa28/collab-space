<%-- 
    Document   : user-chat
    Created on : Aug 15, 2024, 4:12:13?PM
    Author     : srujanaadapa
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Chat</title>
</head>
<body>
    <h2>User Chat</h2>
    <ul>
        <c:forEach var="user" items="${users}">
            <li><a href="/newproject/chat/chat-box/${user.id}">${user.username}</a></li>
        </c:forEach>
    </ul>
    
    <form action="/newproject/chat/dashboard" method="get">
        <input type="submit" value="Back to Dashboard">
    </form>
</body>
</html>
