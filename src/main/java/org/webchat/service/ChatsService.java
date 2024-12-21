package org.webchat.service;


import org.webchat.dto.ChatDto;
import org.webchat.dto.MessageDto;
import org.webchat.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface ChatsService {

    boolean sendNewMessage(String chatId, MessageDto dto);

    Optional<ChatDto> findChatById(String chatId);

    boolean isUserInChat(String userId, String chatId);

    List<String> findAllChatsByUserId(String userId);



    List<MessageDto> findAllMessagesInChat(String chatId);


}
