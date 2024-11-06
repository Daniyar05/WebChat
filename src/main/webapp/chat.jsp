<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
    <title>Чат: ${chat.getName()} </title>
    <style>
        #chat-box {
            border: 1px solid #ccc;
            padding: 10px;
            width: 300px;
            height: 300px;
            overflow-y: scroll;
            resize: both;
        }

        #message-form {
            margin-top: 10px;
        }

        #message-input {
            width: calc(100% - 20px);
            padding: 10px;
            resize: none;
            overflow: hidden;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>

<form action="edit-chat" method="post">
    <label for="name">Chat Name:</label>
    <input type="text" id="name" name="name" value="${chat.getName()}" required>
    <br>
    <input type="submit" value="Update">
    <input type="hidden" name="chatId" value="${chat.idChat}"/>

</form>

<div id="chat-box">
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

<%--<c:set var="chatId" value="${chat.idChat}"/>--%>
<%--<button onclick="deleteData('${chatId}')">Delete Chat</button>--%>
<%--<script>--%>
<%--    function deleteData(chatId) {--%>
<%--        const request = new XMLHttpRequest();--%>
<%--        request.open("DELETE", "edit-chat");--%>
<%--        request.setRequestHeader("chatId", chatId);--%>
<%--        request.send();--%>
<%--    }--%>
<%--</script>--%>
<form id="delete-chat" method="post" action="delete-chat">
    <input type="hidden" name="chatId" value="${chat.idChat}"/>
    <button type="submit">Delete Chat</button>
</form>
</body>
</html>