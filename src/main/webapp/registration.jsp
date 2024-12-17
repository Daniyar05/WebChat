<%--<%@ page contentType="text/html;charset=UTF-8"%>--%>

<%--<html>--%>
<%--<head>--%>
<%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/registration.css">--%>
<%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/main.css">--%>

<%--    <title>Регистрация</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<h2>Форма регистрации</h2>--%>
<%--<form action="registration" method="post">--%>
<%--    <label for="username">Username:</label><br>--%>
<%--    <input type="text" id="username" name="username" required><br><br>--%>

<%--    <label for="password">Password:</label><br>--%>
<%--    <input type="password" id="password" name="password" required><br><br>--%>

<%--    <input type="submit" value="Зарегистрироваться">--%>
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
            <h2>Форма регистрации</h2>
            <form action="registration" method="post">
                <label for="username">Username:</label><br>
                <input type="text" id="username" name="username" required><br><br>

                <label for="password">Password:</label><br>
                <input type="password" id="password" name="password" required><br><br>

                <input type="submit" value="Зарегистрироваться">
            </form>

        </div>
    </main>

    <aside class="left-part"></aside>
</div>
</body>
</html>
