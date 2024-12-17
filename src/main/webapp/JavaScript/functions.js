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

// function createChat(chatId){
//     fetch("list-chats", {
//         method: "POST",
//         headers: {
//             "chatId": chatId
//         }
//     }).then(response => {
//         if (response.ok) {
//             navigateToList(context)
//         }
//     })
// }

// function navigateBack() {
//     if (history.length === 1) {
//         location.href = "/";
//     } else {
//         history.back();
//     }
//     refreshPage();
// }
//
// function refreshPage() {
//     location.reload();
// }