function deleteChat(chatId, context) {
    fetch("edit-chat", {
        method: "DELETE",
        headers: {
            "chatId": chatId
        }
    }).then(response => {
        if (response.ok) {
            navigateToList(context)
        }
    })
}

function navigateToList(context){
    const newUrl = context + "list-chats";
    history.replaceState(null, "", newUrl);
    window.location.href = newUrl;}

function deleteAvatar(chatId, contextPath) {
    fetch(`${contextPath}/avatars?chatId=${chatId}`, {
        method: 'DELETE',
    })
        .then(response => {
            if (response.ok) {
                location.reload();
            }
        })
}