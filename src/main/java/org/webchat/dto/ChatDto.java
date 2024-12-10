package org.webchat.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatDto {

    private String idChat;
    private String name;
    private List<MessageDto> partHistory;

}
