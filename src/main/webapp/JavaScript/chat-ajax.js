const chatBox = document.getElementById('chat-box');
const chatId = chatBox.getAttribute('data-chat-id');
const context = chatBox.getAttribute('context');
function fetchMessages() {
    fetch(`${context}/chat?ID_CHAT=${chatId}`, {
        headers: {
            'Type-Request': 'AjaxRequest', // Указание, что это AJAX-запрос
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            chatBox.innerHTML = '';
            data.forEach(message => {
                const messageDiv = document.createElement('div');
                messageDiv.innerHTML = `<strong>${message.userFrom.username}</strong>: ${message.content}`; // Используем обратные кавычки
                chatBox.appendChild(messageDiv);
            });
            scrollToBottomIfNeeded();
        })
        .catch(error => console.error('Error fetching messages:', error));
}

setInterval(fetchMessages, 3000);
scrollToBottom()


function scrollToBottomIfNeeded() {
    if ((chatBox.scrollHeight - chatBox.scrollTop - chatBox.clientHeight) < chatBox.clientHeight*0.2) {
        chatBox.scrollTop = chatBox.scrollHeight;
    }
}

function scrollToBottom() {
    if (chatBox) {
        chatBox.scrollTop = chatBox.scrollHeight;
    }
}