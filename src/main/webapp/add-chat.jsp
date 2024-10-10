<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Добавление Чата</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        form {
            margin: 20px;
        }
        input[type="text"] {
            padding: 10px;
            margin-bottom: 10px;
            width: 300px;
        }
        input[type="submit"] {
            padding: 10px 15px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

<h2>Добавление нового чата</h2>
<form action="add-chat" method="post">
    <label for="chatId">ID Чата:</label><br>
    <input type="text" id="chatId" name="chatId" required><br>
    <input type="submit" value="Добавить чат">
</form>

</body>
</html>