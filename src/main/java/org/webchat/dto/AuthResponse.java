package org.webchat.dto;

import lombok.*;
import org.webchat.domain.User;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private int status;

    private String statusDesc;

    private User user;

}