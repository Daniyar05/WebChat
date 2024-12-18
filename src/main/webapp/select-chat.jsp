<%--<%@ page contentType="text/html;charset=UTF-8"%>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>

<%--<html>--%>
<%--<head>--%>
<%--    <title>Подбор собеседника</title>--%>
<%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/error.css">--%>
<%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/main.css">--%>
<%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/select-chat.css">--%>
<%--</head>--%>
<%--<body>--%>

<%--<c:if test="${notFoundException == 'true'}">--%>
<%--    <div class="warn-container">--%>
<%--        <h1>Warning</h1>--%>
<%--        <p>Нет подходящего собеседника</p>--%>
<%--    </div>--%>
<%--</c:if>--%>

<%--<div class="container">--%>
<%--    <h2>Найти собеседника</h2>--%>
<%--    <form method="post" action="select-chat">--%>
<%--        <label for="mood">Выберите своё настроение:</label>--%>
<%--        <select id="mood" name="mood" class="select-field">--%>
<%--            <option value="happy">Счастливый</option>--%>
<%--            <option value="sad">Грустный</option>--%>
<%--            <option value="excited">Взволнованный</option>--%>
<%--            <option value="calm">Спокойный</option>--%>
<%--            <!-- Добавление других опций при необходимости -->--%>
<%--        </select>--%>
<%--        <button type="submit" class="button">Начать поиск</button>--%>
<%--    </form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>


<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>

    <title>Подбор собеседника</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/select-chat.css">
</head>
<body>

<div class="container">
    <nav class="sidebar">
        <ul>
            <button onclick="location.href='greeting.jsp'">Главная</button>
            <button onclick="location.href='list-chats'">Мессенджер</button>
            <button onclick="location.href='select-chat'" aria-pressed="true">Подбор собеседника</button>
            <button onclick="location.href='logout'">Выход</button>
        </ul>
    </nav>

    <main class="content">
        <c:if test="${notFoundException == 'true'}">
            <div class="warn-container">
                <h1>Warning</h1>
                <p>Нет подходящего собеседника</p>
            </div>
        </c:if>

        <div class="main-content">
            <h2>Найти собеседника</h2>
            <form method="post" action="select-chat">
                <label for="mood">Выберите своё настроение:</label>
                <select id="mood" name="mood" class="select-field">
                    <option value="happy">Счастливый</option>
                    <option value="sad">Грустный</option>
                    <option value="excited">Взволнованный</option>
                    <option value="calm">Спокойный</option>
                </select>
                <button type="submit" class="button">Начать поиск</button>
            </form>
        </div>
    </main>

    <aside class="left-part">
        <custom:profileButton/>
    </aside>
</div>

</body>
</html>
