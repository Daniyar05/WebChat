package org.webchat.dto.request;

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