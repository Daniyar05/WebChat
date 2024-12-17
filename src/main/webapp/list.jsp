<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Ваши чаты</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/list-chats.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/main.css">
</head>
<body>
<div class="container">
    <nav class="sidebar">
        <ul>
            <button onclick="location.href='greeting'">Главная</button>
            <button onclick="location.href='list-chats'" aria-pressed="true">Мессенджер</button>
            <button onclick="location.href='select-chat'">Подбор собеседника</button>
            <button onclick="location.href='logout'">Выход</button>
        </ul>
    </nav>

    <main class="content">
        <div class="main-content">
            <h1>Ваши чаты</h1>
            <c:if test="${chats != null && !chats.isEmpty()}">
                <ul>
                    <c:forEach var="chat" items="${chats}">
                        <img src="${pageContext.request.contextPath}/avatars?chatId=${chat.getIdChat()}"
                             alt="avatar"
                             style="width: 30px; height: 30px; border-radius: 30%; object-fit: cover;">
                        <li><a href="chat?ID_CHAT=${chat.getIdChat()}">${chat.getName()}</a></li>
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

        <custom:profileButton/>

    </aside>
</div>
</body>
</html>


<%--<%@ page contentType="text/html;charset=UTF-8"  %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>--%>

<%--<html>--%>
<%--<head>--%>
<%--    <script src="<c:url value="/JavaScript/functions.js"/>"></script>--%>
<%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/list-chats.css">--%>
<%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/main.css">--%>
<%--    <title>Ваши чаты</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<custom:profileButton/>--%>
<%--<h1>Ваши чаты</h1>--%>

<%--<c:if test="${chats != null && !chats.isEmpty()}">--%>
<%--    <ul>--%>
<%--        <c:forEach var="chat" items="${chats}" >--%>
<%--            <img src="${pageContext.request.contextPath}/avatars?chatId=${chat.getIdChat()}"--%>
<%--                 alt="avatar"--%>
<%--                 style="width: 30px; height: 30px; border-radius: 30%; object-fit: cover;">--%>

<%--            <li><a href="chat?ID_CHAT=${ chat.getIdChat() }">${chat.getName()}</a></li>--%>

<%--            <c:set var="chatId" value="${chat.idChat}"/>--%>
<%--&lt;%&ndash;            <button onclick="deleteChat('${chatId}', '<c:url value="/"/>')">Delete Chat</button>&ndash;%&gt;--%>

<%--        </c:forEach>--%>
<%--    </ul>--%>
<%--</c:if>--%>

<%--<c:if test="${chats == null || chats.isEmpty()}">--%>
<%--    <p>У вас нет доступных чатов.</p>--%>
<%--</c:if>--%>

<%--<c:set var="chatId" value="${chat.idChat}"/>--%>
<%--<form id="list-chats" method="post" action="list-chats">--%>
<%--    <button type="submit">Создать чат</button>--%>
<%--</form>--%>
<%--<li><a href="add-chat?ID_USER=${user_id}">Добавить чат</a></li>--%>
<%--<div class=“button-container”>--%>
<%--    <button onclick="window.location.href='profile';">Личный кабинет</button>--%>
<%--</div>--%>
<%--<div class=“button-container”>--%>
<%--    <button onclick="window.location.href='select-chat';">Подбор чата</button>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>