<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Welcome to my resource</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/greeting.css">
</head>
<body>

<div class="welcome-container">
    <h1>Welcome to WEB-Chat</h1>

    <div class="button-container">
        <a class="btn" href="${pageContext.request.contextPath}/registration">Registration with new account</a>
        <a class="btn" href="${pageContext.request.contextPath}/login">Log In with existing account</a>
    </div>
</div>

</body>
</html>