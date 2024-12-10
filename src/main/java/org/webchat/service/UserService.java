package org.webchat.service;


import org.webchat.dto.AuthResponse;
import org.webchat.dto.SignInRequest;
import org.webchat.dto.SignUpRequest;

public interface UserService {

    AuthResponse signUp(SignUpRequest request);

    AuthResponse signIn(SignInRequest request);

}
