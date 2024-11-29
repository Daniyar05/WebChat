const chatBox = document.getElementById('chat-box');
const chatId = chatBox.getAttribute('data-chat-id'); // Получаем ID чата из атрибута
const context = chatBox.getAttribute('context');
function fetchMessages() {
    fetch(`${context}/chat?ID_CHAT=${chatId}`, { // Используем обратные кавычки для интерполяции
        headers: {
            'Type-Request': 'AjaxRequest', // Указание, что это AJAX-запрос
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`); // Исправлено использование обратных кавычек
            }
            return response.json();
        })
        .then(data => {
            chatBox.innerHTML = ''; // Очищаем текущие сообщения
            data.forEach(message => {
                const messageDiv = document.createElement('div');
                messageDiv.innerHTML = `<strong>${message.userFrom.username}</strong>: ${message.content}`; // Используем обратные кавычки
                chatBox.appendChild(messageDiv);
            });
        })
        .catch(error => console.error('Error fetching messages:', error));
}

// Периодическое обновление сообщений каждые 5 секунд
setInterval(fetchMessages, 5000);

// Загружаем сообщения сразу при открытии страницы
fetchMessages();
