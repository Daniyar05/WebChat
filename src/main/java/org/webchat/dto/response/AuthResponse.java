package org.webchat.dto.response;

import lombok.*;
import org.webchat.dto.UserDto;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private int status;

    private String statusDesc;

    private UserDto user;

}