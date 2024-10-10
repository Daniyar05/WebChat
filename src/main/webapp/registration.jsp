<!DOCTYPE html>
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

    <label for="email">Email:</label><br>
    <input type="email" id="email" name="email" required><br><br>

    <label for="password">Password:</label><br>
    <input type="password" id="password" name="password" required><br><br>

    <input type="submit" value="Зарегистрироваться">
</form>
</body>
</html>