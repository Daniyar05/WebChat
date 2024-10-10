<%@ page import="org.webchat.domain.Chat" %>
<%@ page import="org.webchat.domain.Message" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="org.webchat.repository.UsersRepoImpl" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>


<%
    Chat currentChat = (Chat) request.getAttribute("chat");
    List<Message> messages = currentChat.getHistory();
%>

<html>
<head>
    <title>Чат: <%= currentChat.getIdChat() %>
    </title>
    <style>
        #chat-box {
            border: 1px solid #ccc;
            padding: 10px;
            width: 300px;
            height: 300px;
            overflow-y: scroll;
            resize: both; /* Позволяет изменять размер как по вертикали, так и по горизонтали */
        }

        #message-form {
            margin-top: 10px;
        }

        #message-input {
            width: calc(100% - 10px); /* Корректировка ширины для отступа */
            padding: 5px;
            resize: horizontal; /* Позволяет изменять размер по горизонтали */
            overflow: auto; /* Обеспечивает корректное отображение текста */
        }
    </style>
</head>
<body>
<h1>Чат: <%= currentChat.getIdChat() %>
</h1>
<div id="chat-box">
    <%
        for (Message message : messages) {
    %>
    <div><strong><%= UsersRepoImpl.getUser(message.idFrom()).get().getUsername() %>:</strong> <%= message.content() %>
    </div>
    <%
        }
    %>
</div>
<form id="message-form" method="post" action="chat?ID_CHAT=<%=currentChat.getIdChat()%>">
    <label>
        <textarea id="message-input" name="content" placeholder="Введите сообщение" required></textarea>
    </label>
    <input type="hidden" name="chatId" value="<%= currentChat.getIdChat() %>"/>
    <button type="submit">Отправить</button>
</form>
</body>
</html>