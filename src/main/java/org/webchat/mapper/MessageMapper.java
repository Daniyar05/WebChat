package org.webchat.mapper;


import org.webchat.domain.Message;
import org.webchat.dto.MessageDto;

public interface MessageMapper {

    MessageDto toDto(Message entity);
}
