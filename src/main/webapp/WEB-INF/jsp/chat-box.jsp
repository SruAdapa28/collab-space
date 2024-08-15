<%-- 
    Document   : chat-box
    Created on : Aug 15, 2024, 11:27:30?AM
    Author     : srujanaadapa
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Chat Box</title>
</head>
<body>
    <h2>Chat with ${selectedUser.username}</h2>

    <div id="chatHistory">
        <c:forEach var="message" items="${chatHistory}">
            <p><strong>${message.sender.username}:</strong> ${message.content} <em>${message.timestamp}</em></p>
        </c:forEach>
    </div>

    <form action="/newproject/chat/sendMessage" method="post">
        <input type="hidden" name="recipientId" value="${selectedUser.id}">
        <textarea name="messageText" rows="4" cols="50" required></textarea><br><br>
        <input type="submit" value="Send">
    </form>

    <form action="/newproject/chat/user-chat" method="get">
        <input type="submit" value="Back to User List">
    </form>
</body>
</html>

