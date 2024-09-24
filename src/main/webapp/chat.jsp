
<%@ page import="org.webchat.domain.Chat" %>
<%@ page import="org.webchat.domain.Message" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>


<%
    // Я предполагаю наличие текущего объекта User и Chat (например, загруженных из сессии или контекста).
    Chat currentChat = (Chat) request.getAttribute("chat");
    List<Message> messages = currentChat.getHistory();

    // Получаем имя текущего пользователя (возможно, из сессии)
%>

<html>
<head>
    <title>Чат: <%= currentChat.getIdChat() %></title>
    <style>
        #chat-box {
            border: 1px solid #ccc;
            padding: 10px;
            width: 300px;
            height: 300px;
            overflow-y: scroll;
        }
        #message-form {
            margin-top: 10px;
        }
    </style>
</head>
<body>
<h1>Чат: <%= currentChat.getIdChat() %></h1>
<div id="chat-box">
    <%
        for (Message message : messages) {
    %>
    <div><strong><%= message.idFrom() %>:</strong> <%= message.content() %></div>
    <%
        }
    %>
</div>
<form id="message-form" method="post" action="chat?ID_CHAT=<%=currentChat.getIdChat()%>">
    <label>
        <input type="text" name="content" placeholder="Введите сообщение" required />
    </label>
    <input type="hidden" name="chatId" value="<%= currentChat.getIdChat() %>" />
    <button type="submit">Отправить</button>
</form>
</body>
</html>