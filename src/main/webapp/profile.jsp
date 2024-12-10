<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/error.css">
</head>
<body>
<c:if test="${error == 'true'}">
    <div class="warn-container">
        <h1>Warning</h1>
        <p>Имя уже существует</p>
    </div>
</c:if>
<h2>Profile</h2>

<form action="profile" method="post">
    <label for="name">Username:</label>
    <input type="text" id="name" name="name" value="${username}" required>
    <br>
    <input type="submit" value="Update">
</form>

<div class=“button-container”>
    <button onclick="window.location.href='list-chats';">Переход к списку чатов</button>
</div>

</body>
</html>