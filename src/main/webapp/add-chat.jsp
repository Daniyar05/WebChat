<%--<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>--%>

<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <title>Добавление Чата</title>--%>
<%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/add-chat.css">--%>
<%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/main.css">--%>

<%--</head>--%>
<%--<body>--%>

<%--<h2>Добавление нового чата</h2>--%>
<%--<form action="add-chat" method="post">--%>
<%--    <link href="CSS/styles.css" rel=“stylesheet”>--%>
<%--    <label for="chatId">ID Чата:</label><br>--%>
<%--    <input type="text" id="chatId" name="chatId" required><br>--%>
<%--    <input type="submit" value="Добавить чат">--%>
<%--</form>--%>

<%--</body>--%>
<%--</html>--%>


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
    <!-- Левая панель с действиями -->
    <nav class="sidebar">
        <ul>
            <button onclick="location.href='greeting'">Главная</button>
            <button onclick="location.href='list-chats'">Мессенджер</button>
            <button onclick="location.href='select-chat'">Подбор собеседника</button>
            <button onclick="location.href='logout'">Выход</button>
        </ul>
    </nav>

    <!-- Основное содержимое -->
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

    <!-- Правая панель с профилем -->
    <aside class="left-part">
        <%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>

        <custom:profileButton/>
    </aside>
</div>
</body>
</html>
