package org.webchat.mapper;

import org.webchat.domain.Chat;
import org.webchat.dto.ChatDto;

public interface ChatMapper {

    ChatDto toDto(Chat entity);

}
