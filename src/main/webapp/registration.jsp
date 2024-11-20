<%@ page contentType="text/html;charset=UTF-8"%>

<html>
<head>
    <title>Регистрация</title>
</head>
<body>
<h2>Форма регистрации</h2>
<form action="registration" method="post">
    <label for="username">Username:</label><br>
    <input type="text" id="username" name="username" required><br><br>

    <label for="password">Password:</label><br>
    <input type="password" id="password" name="password" required><br><br>

    <input type="submit" value="Зарегистрироваться">
</form>

<div class=“button-container”>
    <button onclick="window.location.href='greeting.jsp';">Переход к стартовому меню</button>
</div>
</body>
</html>