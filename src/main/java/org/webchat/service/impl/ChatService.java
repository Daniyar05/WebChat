package org.webchat.service.impl;

import org.webchat.domain.Chat;
import org.webchat.domain.Message;
import org.webchat.domain.User;
import org.webchat.repository.ChatRepo;
import org.webchat.repository.UserRepo;
import org.webchat.usecase.Root;

import java.util.List;
import java.util.Optional;

public class ChatService {
    private final ChatRepo chatRepo;
    private final UserRepo userRepo;
    public ChatService(ChatRepo chatRepo, UserRepo userRepo) {
        this.chatRepo=chatRepo;
        this.userRepo=userRepo;
    }

    public Optional<Chat> getChatById(String idChat) {
        return chatRepo.getChat(idChat);
    }

    public boolean isUserInChat(String userId, String chatId) {
        Optional<User> user = userRepo.getUser(userId);
        return user.isPresent() && user.get().getIdChats().contains(chatId);
    }

    public List<Message> getMessages(String chatId, int offset, int limit) {
        Optional<Chat> chat = chatRepo.getChat(chatId);
        return chat.flatMap(c -> c.getHistory(offset, limit)).orElse(List.of());
//            return chat.flatMap(c ->
//                            c.getHistory(offset, limit)
//                                    .stream()
//                                    .sorted(Comparator.comparing(Message::getDate)) // Сортировка по дате
//                                    .toList()
//                    ).orElse(List.of());

    }

    public void addMessage(String chatId, Message message) {
        chatRepo.addMessage(chatId, message);
    }

    public void deleteChat(String chatId) {
        chatRepo.deleteChat(chatId);
    }
}
