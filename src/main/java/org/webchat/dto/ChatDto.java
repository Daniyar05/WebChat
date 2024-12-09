package org.webchat.dto;

import lombok.*;
import org.webchat.domain.Message;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatDto {

    private String idChat;
    private String name;
    private List<Message> partHistory;

}
