
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Добавление Чата</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/add-chat.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/main.css">
</head>
<body>
<div class="container">
    <nav class="sidebar">
        <ul>
            <button onclick="location.href='greeting.jsp'">Главная</button>
            <button onclick="location.href='list-chats'">Мессенджер</button>
            <button onclick="location.href='select-chat'">Подбор собеседника</button>
            <button onclick="location.href='logout'">Выход</button>
        </ul>
    </nav>

    <main class="content">
        <div class="main-content">
            <h2>Добавление нового чата</h2>
            <form action="add-chat" method="post">
                <label for="chatId">ID Чата:</label>
                <input type="text" id="chatId" name="chatId" required>
                <button type="submit" class="button">Добавить чат</button>
            </form>
        </div>
    </main>

    <aside class="left-part">
        <%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>

        <custom:profileButton/>
    </aside>
</div>
</body>
</html>
