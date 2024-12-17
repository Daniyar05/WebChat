package org.webchat.service.impl;

import com.google.gson.Gson;
import org.webchat.domain.Chat;
import org.webchat.domain.Message;
import org.webchat.domain.User;
import org.webchat.usecase.Root;

import java.util.List;
import java.util.Optional;

public class ChatService {
    public Optional<Chat> getChatById(String idChat) {
        return Root.chatRepo.getChat(idChat);
    }

    public boolean isUserInChat(String userId, String chatId) {
        Optional<User> user = Root.usersRepo.getUser(userId);
        return user.isPresent() && user.get().getIdChats().contains(chatId);
    }

    public List<Message> getMessages(String chatId, int offset, int limit) {
        Optional<Chat> chat = Root.chatRepo.getChat(chatId);
        return chat.flatMap(c -> c.getHistory(offset, limit)).orElse(List.of());
//            return chat.flatMap(c ->
//                            c.getHistory(offset, limit)
//                                    .stream()
//                                    .sorted(Comparator.comparing(Message::getDate)) // Сортировка по дате
//                                    .toList()
//                    ).orElse(List.of());

    }

    public void addMessage(String chatId, Message message) {
        Root.chatRepo.addMessage(chatId, message);
    }

    public void deleteChat(String chatId) {
        Root.chatRepo.deleteChat(chatId);
    }
}
