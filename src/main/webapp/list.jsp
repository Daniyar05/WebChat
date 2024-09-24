
<%@ page import="java.util.List" %>
<%@ page import="org.webchat.domain.Chat" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ваши чаты</title>
</head>
<body>
<h1>Ваши чаты</h1>

<%
    @SuppressWarnings("unchecked")
    List<Chat> chats = (List<Chat>) request.getAttribute("chats"); // Получаем список чатов из атрибутов запроса.
    if (chats != null && !chats.isEmpty()) {
%>
<ul>
    <%for (Chat chat : chats) {%>
        <li><a href="chat?ID_CHAT=<%= chat.getIdChat() %>"><%= chat.getIdChat() %></a></li>
    <%}%>
</ul>
<%
} else {
%>
<p>У вас нет доступных чатов.</p>
<%
    }
%>
<li> <a href="create-chat">Создать чат</a> </li>
</body>
</html>