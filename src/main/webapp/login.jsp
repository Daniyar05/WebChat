<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/login.css">--%>
<%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/main.css">--%>

<%--    <title>Login Page</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<form method="post" action="login">--%>
<%--    <label for="username">Username:</label>--%>
<%--    <input type="text" name="username" id="username" required/><br/>--%>
<%--    <label for="password">Password:</label>--%>
<%--    <input type="password" name="password" id="password" required/><br/>--%>
<%--    <input type="submit" value="Login"/>--%>
<%--</form>--%>
<%--<div class=“button-container”>--%>
<%--    <button onclick="window.location.href='greeting.jsp';">Переход к стартовому меню</button>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>



<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Вход</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/login.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/main.css">
</head>
<body>
<div class="container">
    <nav class="sidebar">
        <ul>
            <button onclick="location.href='greeting.jsp'">Главная</button>
        </ul>
    </nav>

    <main class="content">
        <div class="main-content">
            <h2>Вход</h2>
            <form method="post" action="login">
                <label for="username">Имя пользователя:</label>
                <input type="text" id="username" name="username" required>
                <label for="password">Пароль:</label>
                <input type="password" id="password" name="password" required>
                <button type="submit" class="button">Войти</button>
            </form>
        </div>
    </main>

    <aside class="left-part"></aside>
</div>
</body>
</html>
