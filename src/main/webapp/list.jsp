<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Ваши чаты</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/CSS/list-chats.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/CSS/main.css' />">
</head>
<body>
<div class="container">
    <nav class="sidebar">
        <ul>
            <button onclick="location.href='<c:url value="/greeting.jsp" />'">Главная</button>
            <button onclick="location.href='<c:url value="/list-chats" />'" aria-pressed="true">Мессенджер</button>
            <button onclick="location.href='<c:url value="/select-chat" />'">Подбор собеседника</button>
            <button onclick="location.href='<c:url value="/logout" />'">Выход</button>
        </ul>
    </nav>

    <main class="content">
        <div class="main-content">
            <div class="button-container">
                <button class="btn" onclick="location.href='<c:url value="/add-chat" />'">Добавить чат</button>
                <button class="btn" id="createChatBtn">Создать чат</button>
            </div>
            <h1>Ваши чаты</h1>
            <c:if test="${chats != null && !chats.isEmpty()}">
                <ul>
                    <c:forEach var="chat" items="${chats}">
                        <li class="chat-item">
                            <img src="<c:url value='/avatars' />?chatId=<c:out value='${chat.idChat}' />"
                                 alt="avatar"
                                 class="chat-avatar">
                            <a href="<c:url value='/chat' />?ID_CHAT=<c:out value='${chat.idChat}' />" class="chat-link">
                                <c:out value="${chat.name}" />
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </c:if>
            <c:if test="${chats == null || chats.isEmpty()}">
                <p>У вас нет доступных чатов.</p>
            </c:if>
        </div>
    </main>

    <aside class="left-part">
        <%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
        <custom:profileButton />
    </aside>
</div>

<script>
    document.getElementById('createChatBtn').addEventListener('click', function () {
        fetch('<c:url value="/list-chats" />', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(response => {
            if (response.ok) {
                window.location.reload();
            }
        });
    });
</script>

</body>
</html>
