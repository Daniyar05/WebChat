package org.webchat.mapper.impl;


import org.webchat.domain.Chat;
import org.webchat.dto.ChatDto;
import org.webchat.mapper.ChatMapper;

public class ChatMapperImpl implements ChatMapper {
    @Override
    public ChatDto toDto(Chat chat) {
        return ChatDto.builder()
                .idChat(chat.getIdChat())
                .name(chat.getName())
                .partHistory(chat.getHistory())
                .build();
    }
}
