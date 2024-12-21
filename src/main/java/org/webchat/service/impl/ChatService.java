package org.webchat.service.impl;

import org.webchat.domain.Chat;
import org.webchat.domain.Message;
import org.webchat.domain.User;
import org.webchat.repository.ChatRepo;
import org.webchat.repository.UserRepo;

import java.util.List;
import java.util.Optional;

public class ChatService {
    private final ChatRepo chatRepo;
    private final UserRepo userRepo;

    public ChatService(ChatRepo chatRepo, UserRepo userRepo) {
        this.chatRepo = chatRepo;
        this.userRepo = userRepo;
    }

    public Optional<Chat> getChatById(String idChat) {
        return chatRepo.getChat(idChat);
    }

    public List<Message> getMessages(String chatId, int offset, int limit) {
        return chatRepo.getHistoryForChat(chatId, offset, limit).orElse(List.of());
    }

    public boolean canUserAccessChat(String userId, String chatId) {
        if (userId == null) return false;
        Optional<User> user = userRepo.getUser(userId);
        return user.isPresent() && user.get().getIdChats().contains(chatId);
    }

    public boolean addMessage(String userId, String chatId, String content) {
        if (userId == null || chatId == null || content == null || content.trim().isEmpty()) {
            return false;
        }

        Optional<User> user = userRepo.getUser(userId);
        if (user.isPresent()) {
            Message newMessage = new Message(user.get(), content);
            return chatRepo.addMessage(chatId, newMessage);
        }
        return false;
    }

    public boolean deleteChat(String chatId) {
        if (chatId == null || chatId.isBlank()) {
            return false;
        }
        return chatRepo.deleteChat(chatId);
    }
}
