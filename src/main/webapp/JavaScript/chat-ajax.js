let offset = 0;
const limit = 20;
let isLoading = false;

const chatBox = document.getElementById('chat-box');
const chatId = chatBox.getAttribute('data-chat-id');
const context = chatBox.getAttribute('context');

fetchMessages(offset, limit);

const loadedMessages = new Set();
function fetchMessages(offset, limit, prepend = false) {
    if (isLoading) return;
    isLoading = true;
    // console.log("fetching message")
    fetch(`${context}/chat?ID_CHAT=${chatId}&offset=${offset}&limit=${limit}`, {
        headers: {
            'X-Type-Request': 'AjaxRequest', // Указание, что это AJAX-запрос
        }
    })

        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            console.log(data);

            const fragment = document.createDocumentFragment();
            data.forEach(message => {
                const messageDiv = document.createElement('div');
                const uniqueKey = `${message.userFrom.username}:${message.content}:${new Date(message.date).toISOString()}`;
                if (!loadedMessages.has(uniqueKey)) {
                    loadedMessages.add(uniqueKey);
                    const usernameText = document.createElement("strong");
                    usernameText.innerText = message.userFrom.username
                    messageDiv.appendChild(usernameText)
                    const messageText = document.createElement("span")
                    messageText.innerText = `> ${message.content.trim()}`
                    // messageText.innerHTML+='<br/>';
                    messageDiv.appendChild(messageText)
                    if (prepend) {
                        fragment.prepend(messageDiv);
                    } else {
                        fragment.append(messageDiv);
                    }
                }
            });

            if (prepend) {
                chatBox.prepend(fragment);
            } else {
                chatBox.append(fragment);
            }
            isLoading = false;
        })
        .catch(error => {
            console.error('Error fetching messages:', error);
            isLoading = false;
        });

}

function scrollToBottom() {
    if (chatBox) {
        chatBox.scrollTop = chatBox.scrollHeight;
    }
}

chatBox.addEventListener('scroll', () => {
    if (chatBox.scrollTop < 100 && !isLoading) {
        fetchMessages(offset, limit, true);
        offset += limit;
    }
});

