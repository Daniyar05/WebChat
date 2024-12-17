<%--<%@ page contentType="text/html;charset=UTF-8"%>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <script src="<c:url value="/JavaScript/functions.js"/>"></script>--%>
<%--    <title>Чат: ${chat.getName()} </title>--%>
<%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/chat.css">--%>
<%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/main.css">--%>

<%--</head>--%>
<%--<body>--%>

<%--<form action="edit-chat" method="post">--%>
<%--    <label for="name">Chat Name:</label>--%>
<%--    <input type="text" id="name" name="name" value="${chat.getName()}" required>--%>
<%--    <br>--%>
<%--    <input type="submit" value="Update">--%>
<%--    <input type="hidden" name="chatId" value="${chat.idChat}"/>--%>
<%--</form>--%>
<%--<div id="chat-box" data-chat-id="${chat.idChat}" context='<c:url value="/"/>' data-messages='${messagesJson}'>--%>
<%--&lt;%&ndash;    <c:forEach var="message" items="${chat.getHistory()}">&ndash;%&gt;--%>
<%--&lt;%&ndash;        <div>&ndash;%&gt;--%>
<%--&lt;%&ndash;            <strong>${message.userFrom().getUsername()}</strong>: ${message.content()}&ndash;%&gt;--%>
<%--&lt;%&ndash;        </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;    </c:forEach>&ndash;%&gt;--%>
<%--</div>--%>

<%--<form id="message-form" method="post" action="chat?ID_CHAT=${chat.idChat}">--%>
<%--    <label>--%>
<%--        <textarea id="message-input" name="content" placeholder="Введите сообщение" required></textarea>--%>
<%--    </label>--%>
<%--    <input type="hidden" name="chatId" value="${chat.idChat}"/>--%>
<%--    <button type="submit">Отправить</button>--%>
<%--</form>--%>


<%--</body>--%>
<%--</html>--%>


<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="<c:url value="/JavaScript/functions.js"/>"></script>
    <title>Чат</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/chat.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/main.css">
</head>
<body>
<div class="container">
    <nav class="sidebar">
        <ul>
            <button onclick="location.href='main'">Главная</button>
            <button onclick="location.href='list-chats'" aria-pressed="true">Мессенджер</button>
            <button onclick="location.href='select-chat'">Подбор собеседника</button>
            <button onclick="location.href='logout'">Выход</button>
        </ul>
    </nav>

    <main class="content">
        <div class="main-content">
            <form action="edit-chat" method="post">
                <label for="name">Chat Name:</label>
                <input type="text" id="name" name="name" value="${chat.getName()}" required>
                <br>
                <input type="submit" value="Update">
                <input type="hidden" name="chatId" value="${chat.idChat}"/>
            </form>

<%--            <h1>Чат: ${chat.getName()}</h1>--%>
            <div id="chat-box" data-chat-id="${chat.idChat}" context="${pageContext.request.contextPath}" data-messages='${messagesJson}'>
                <!-- История сообщений -->
            </div>

            <form id="message-form" method="post" action="chat?ID_CHAT=${chat.idChat}">
                <label>
                    <textarea id="message-input" name="content" placeholder="Введите сообщение" required></textarea>
                </label>
                <input type="hidden" name="chatId" value="${chat.idChat}"/>
                <button type="submit">Отправить</button>
            </form>
            <button onclick="deleteChat('${chat.idChat}', '<c:url value="/"/>')">Delete Chat</button>

        </div>
    </main>

    <aside class="left-part">
        <%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>

        <custom:profileButton/>

    </aside>
</div>
<c:set var="chatId" value="${chat.idChat}"/>
<script src="<c:url value='/JavaScript/chat-function.js'/>"></script>
<script src="<c:url value='/JavaScript/chat-ajax.js'/>"></script>

</body>
</html>
