<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
    <script src="<c:url value="/JavaScript/functions.js"/>"></script>
    <title>Чат: ${chat.getName()} </title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/chat.css">

</head>
<body>

<form action="edit-chat" method="post">
    <label for="name">Chat Name:</label>
    <input type="text" id="name" name="name" value="${chat.getName()}" required>
    <br>
    <input type="submit" value="Update">
    <input type="hidden" name="chatId" value="${chat.idChat}"/>
</form>

<div id="chat-box" data-chat-id="${chat.idChat}" context='<c:url value="/"/>'>
    <c:forEach var="message" items="${chat.getHistory()}">
        <div>
            <strong>${message.userFrom().getUsername()}</strong>: ${message.content()}
        </div>
    </c:forEach>
</div>

<form id="message-form" method="post" action="chat?ID_CHAT=${chat.idChat}">
    <label>
        <textarea id="message-input" name="content" placeholder="Введите сообщение" required></textarea>
    </label>
    <input type="hidden" name="chatId" value="${chat.idChat}"/>
    <button type="submit">Отправить</button>
</form>

<c:set var="chatId" value="${chat.idChat}"/>
<button onclick="deleteChat('${chatId}', '<c:url value="/"/>')">Delete Chat</button>
<script src="<c:url value='/JavaScript/chat-ajax.js'/>"></script>

</body>
</html>
