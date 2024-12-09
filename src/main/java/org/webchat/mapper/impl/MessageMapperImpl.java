package org.webchat.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.webchat.domain.Message;
import org.webchat.dto.MessageDto;
import org.webchat.mapper.MessageMapper;
import org.webchat.usecase.Root;

@RequiredArgsConstructor
public class MessageMapperImpl implements MessageMapper {

    @Override
    public MessageDto toDto(Message message) {
        return MessageDto.builder()
                .content(message.content())
                .authorUsername(message.userFrom().getUsername())
                .build();
    }
}
