package org.webchat.dto;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest {

    private String username;

    private String password;

}