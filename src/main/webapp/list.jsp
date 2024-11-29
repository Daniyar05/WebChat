<%@ page contentType="text/html;charset=UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <script src="<c:url value="/JavaScript/functions.js"/>"></script>
    <title>Ваши чаты</title>
</head>
<body>
<h1>Ваши чаты</h1>

<c:if test="${chats != null && !chats.isEmpty()}">
    <ul>
        <c:forEach var="chat" items="${chats}" >
            <li><a href="chat?ID_CHAT=${ chat.getIdChat() }">${chat.getName()}</a></li>

            <c:set var="chatId" value="${chat.idChat}"/>
            <button onclick="deleteChat('${chatId}', '<c:url value="/"/>')">Delete Chat</button>

        </c:forEach>
    </ul>
</c:if>

<c:if test="${chats == null || chats.isEmpty()}">
    <p>У вас нет доступных чатов.</p>
</c:if>

<c:set var="chatId" value="${chat.idChat}"/>
<form id="list-chats" method="post" action="list-chats">
    <button type="submit">Создать чат</button>
</form>
<li><a href="add-chat?ID_USER=${user_id}">Добавить чат</a></li>
<div class=“button-container”>
    <button onclick="window.location.href='profile';">Личный кабинет</button>
</div>
<div class=“button-container”>
    <button onclick="window.location.href='select-chat';">Подбор чата</button>
</div>
</body>
</html>