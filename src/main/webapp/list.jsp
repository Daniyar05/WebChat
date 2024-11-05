<%@ page contentType="text/html;charset=UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Ваши чаты</title>
</head>
<body>
<h1>Ваши чаты</h1>

<c:if test="${chats != null && !chats.isEmpty()}">
    <ul>
        <c:forEach var="chat" items="${chats}" >
            <li><a href="chat?ID_CHAT=${ chat.getIdChat() }">${chat.getName()}</a></li>
            <form id="delete-chat" method="post" action="delete-chat">
                <input type="hidden" name="chatId" value="${chat.getIdChat()}"/>
                <button type="submit">Delete Chat</button>
            </form>

        </c:forEach>
    </ul>
</c:if>

<c:if test="${chats == null || chats.isEmpty()}">
    <p>У вас нет доступных чатов.</p>
</c:if>

<li><a href="create-chat?ID_USER=${user_id}">Создать чат</a></li>
<li><a href="add-chat?ID_USER=${user_id}">Добавить чат</a></li>
<div class=“button-container”>
    <button onclick="window.location.href='profile';">Личный кабинет</button>
</div>
<div class=“button-container”>
    <button onclick="window.location.href='select-chat';">Подбор чата</button>
</div>
</body>
</html>