package org.webchat.service;


import org.webchat.dto.response.AuthResponse;
import org.webchat.dto.request.SignInRequest;
import org.webchat.dto.request.SignUpRequest;

public interface UserService {

    AuthResponse signUp(SignUpRequest request);

    AuthResponse signIn(SignInRequest request);

}
