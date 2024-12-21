<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="<c:url value="/JavaScript/functions.js"/>"></script>
    <title>Чат: <c:out value="${chat.name}" /></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/chat.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/main.css">
</head>
<body>
<div class="container">
    <nav class="sidebar">
        <ul>
            <button onclick="location.href='greeting.jsp'">Главная</button>
            <button onclick="location.href='list-chats'" aria-pressed="true">Мессенджер</button>
            <button onclick="location.href='select-chat'">Подбор собеседника</button>
            <button onclick="location.href='logout'">Выход</button>
        </ul>
    </nav>

    <main class="content">
        <div class="main-content">
            <div class="avatar-container">
                <button class="avatar-button">
                    <img src="<c:url value='/avatars' />?chatId=<c:out value='${chat.idChat}' />"
                         alt="avatar"
                         style="width: 30px; height: 30px; border-radius: 30%; object-fit: cover;">
                </button>
                <div class="avatar-actions">
                    <form id="update-avatar-form" enctype="multipart/form-data" method="post" action="<c:url value='/avatars' />">
                        <input type="hidden" name="chatId" value="<c:out value='${chat.idChat}' />">
                        <label for="avatar-input">Загрузить аватарку:</label>
                        <input type="file" id="avatar-input" name="avatar" accept="image/*">
                        <button type="submit">Изменить</button>
                    </form>
                    <button onclick="deleteAvatar('<c:out value='${chat.idChat}' />', '<c:url value='/' />')">Удалить</button>
                </div>
            </div>

            <form action="edit-chat" method="post">
                <input type="text" id="name" name="name" value="<c:out value='${chat.name}' />" required>
                <br>
                <input type="submit" value="Update">
                <input type="hidden" name="chatId" value="<c:out value='${chat.idChat}' />"/>
            </form>

            <div id="chat-box" data-chat-id="<c:out value='${chat.idChat}' />"
                 context="<c:url value='/' />"
                 data-messages="<c:out value='${messagesJson}' escapeXml='true' />">
            </div>
            <button id="scroll-to-bottom" class="scroll-button" onclick="scrollToBottom()">↓</button>

            <form id="message-form" method="post">
<%--                action="chat?ID_CHAT=<c:out value='${chat.idChat}' />--%>
                <label>
                    <textarea id="message-input" name="content" placeholder="Введите сообщение" required></textarea>
                </label>
                <input type="hidden" name="chatId" value="<c:out value='${chat.idChat}' />"/>
                <button id='button' type="submit">Отправить</button>
            </form>
            <button onclick="deleteChat('<c:out value='${chat.idChat}' />', '<c:url value='/' />')">Delete Chat</button>
        </div>
    </main>

    <aside class="left-part">
        <%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>
        <custom:profileButton />
    </aside>
</div>
<c:set var="chatId" value="<c:out value='${chat.idChat}' />" />
<script src="<c:url value='/JavaScript/chat-ajax.js' />"></script>

<script>
    const url1 = new URL(window.location.href);
    const websocketUrl = 'ws://' + url1.host + '${pageContext.request.contextPath}/websocket';
    var ws = new WebSocket(websocketUrl);
    ws.onopen = function () {
        console.log("Connected to ws")
    }
    ws.onerror = function () {
        console.log("Error with ws")
    }
    ws.onmessage = function processMessage(message) {
        var json = JSON.parse(message.data);
        if(json.chatId === '${chat.idChat}') {
            const messageBlock = document.getElementById('chat-box');
            const fragment = document.createDocumentFragment();
            const messageDiv = document.createElement('div');

            messageDiv.innerText += json.username.trim() +'> '+ json.message.trim();
            fragment.append(messageDiv)
            messageBlock.append(fragment)
        }
    }
    document.getElementById('button').onclick = function() {
        var textFieldValue = document.getElementById('message-input').value;
        if(textFieldValue.trim() !== '') {
            var sendJs = {
                "chatId": '${chat.idChat}',
                "message": textFieldValue,
                "userId": '${userId}',
                "username":'${username}'
            };
            var sendJsString = JSON.stringify(sendJs);
            ws.send(sendJsString);
        }
        document.getElementById('message-input').value = ""

    };

</script>
<script>
    document.getElementById("update-avatar-form").addEventListener("submit", function (event) {
        event.preventDefault();
        const formData = new FormData(this);

        fetch(this.action, {
            method: 'POST',
            body: formData,
        })
            .then(response => {
                if (response.ok) {
                    location.reload();
                }
            })
    });
</script>
</body>
</html>
