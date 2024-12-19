package org.webchat.repository.Impl;


import org.webchat.domain.Chat;
import org.webchat.domain.Message;
import org.webchat.domain.User;
import org.webchat.repository.DAO.ChatDAO;
import org.webchat.repository.ChatRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ChatRepoImpl implements ChatRepo {
    public ChatRepoImpl(ChatDAO chatDAO) {
        this.chatDAO = chatDAO;
    }

    private final ChatDAO chatDAO;


    public Optional<Chat> getChat(String idChat) {
        return chatDAO.getChat(idChat);
    }

    public Optional<List<Message>> getHistoryForChat(String chatId, int offset, int limit) {
        Chat chat = getChat(chatId).orElseThrow(() -> new IllegalArgumentException("Chat not found"));
        return Optional.of(chat.getHistory().stream()
                .skip(offset)
                .limit(limit)
                .collect(Collectors.toList()));
    }


    public boolean addChat(Chat chat) {
        return chatDAO.addChat(chat);
    }
    public boolean addMessage(String idChat, Message message){
        return chatDAO.addMessage(idChat, message);
    }
    public boolean renameChat(String idChat, String newName){
        return chatDAO.renameChat(idChat, newName);
    }
    public boolean deleteChat(String idChat){
        return chatDAO.deleteChat(idChat);
    }
    public boolean hasUserById(String userId, String chatId){return chatDAO.hasUserById(userId, chatId);}

    @Override
    public List<String> findAllUsersInChat(String chatId) {
        return chatDAO.getUsersInChatById(chatId);
    }

}
