<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Profile</title>
</head>
<body>
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