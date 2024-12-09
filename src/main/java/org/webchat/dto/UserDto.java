package org.webchat.dto;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String id;
    private String username;

}
