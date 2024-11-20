<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
<form method="post" action="login">
    <label for="username">Username:</label>
    <input type="text" name="username" id="username" required/><br/>
    <label for="password">Password:</label>
    <input type="password" name="password" id="password" required/><br/>
    <input type="submit" value="Login"/>
</form>
<div class=“button-container”>
    <button onclick="window.location.href='greeting.jsp';">Переход к стартовому меню</button>
</div>
</body>
</html>