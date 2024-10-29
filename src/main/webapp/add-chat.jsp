<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Добавление Чата</title>

</head>
<body>

<h2>Добавление нового чата</h2>
<form action="add-chat" method="post">
    <link href="CSS/styles.css" rel=“stylesheet”>
    <label for="chatId">ID Чата:</label><br>
    <input type="text" id="chatId" name="chatId" required><br>
    <input type="submit" value="Добавить чат">
</form>

</body>
</html>