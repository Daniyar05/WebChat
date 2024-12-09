package org.webchat.dto;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

    private String username;
    private String password;


}