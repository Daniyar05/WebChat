package org.webchat.mapper.impl;


import org.webchat.domain.Chat;
import org.webchat.domain.Message;
import org.webchat.dto.ChatDto;
import org.webchat.dto.MessageDto;
import org.webchat.mapper.ChatMapper;
import org.webchat.usecase.Root;

import java.util.Collections;
import java.util.List;

public class ChatMapperImpl implements ChatMapper {
    @Override
    public ChatDto toDto(Chat chat) {
        return ChatDto.builder()
                .idChat(chat.getIdChat())
                .name(chat.getName())
                .partHistory(toDto(chat.getHistory()))
                .build();
    }

    private List<MessageDto> toDto(List<Message> messageList) {
        return messageList.stream().map(this::toDto).toList();

    }
    private MessageDto toDto(Message message) {
        return MessageDto.builder()
                .content(message.content())
                .authorUsername(message.userFrom().getUsername())
                .build();
    }
}
