<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Подбор собеседника</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/error.css">
    <style>
        .container {
            width: 500px;
            margin: 0 auto;
            text-align: center;
        }

        .button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-top: 20px;
        }
        .select-field {
            padding: 10px;
            border-radius: 5px;
            margin: 10px 0;
        }
    </style>
</head>
<body>

<c:if test="${notFoundException == 'true'}">
    <div class="warn-container">
        <h1>Warning</h1>
        <p>Нет подходящего собеседника</p>
    </div>
</c:if>

<div class="container">
    <h2>Найти собеседника</h2>
    <form method="post" action="select-chat">
        <label for="mood">Выберите своё настроение:</label>
        <select id="mood" name="mood" class="select-field">
            <option value="happy">Счастливый</option>
            <option value="sad">Грустный</option>
            <option value="excited">Взволнованный</option>
            <option value="calm">Спокойный</option>
            <!-- Добавление других опций при необходимости -->
        </select>
        <button type="submit" class="button">Начать поиск</button>
    </form>
</div>
</body>
</html>
