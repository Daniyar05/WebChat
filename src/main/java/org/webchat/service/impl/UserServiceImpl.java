package org.webchat.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.webchat.domain.User;
import org.webchat.dto.AuthResponse;
import org.webchat.dto.SignInRequest;
import org.webchat.dto.SignUpRequest;
import org.webchat.dto.UserDto;
import org.webchat.mapper.UserMapper;
import org.webchat.repository.Impl.UsersRepoImpl;
import org.webchat.service.UserService;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UsersRepoImpl userRepository;

    private final UserMapper userMapper;

    @Override
    public AuthResponse signUp(SignUpRequest request) {

        if(request.getPassword() == null || request.getPassword().isBlank())
            return response(2, "Empty password", null);

        if(request.getUsername() == null || request.getUsername().isBlank())
            return response(3, "Empty nickname", null);

//        if(!AuthUtils.checkPassword(request.getPassword()))
//            return response(5, "Weak password", null);

        if(userRepository.getUserByUsername(request.getUsername()).isPresent())
            return response(7, "Nickname taken", null);

        userRepository.addUser(userMapper.toEntity(request), request.getPassword());
        Optional<User> optionalUser = userRepository.getUserByUsername(request.getUsername());
        if(optionalUser.isEmpty())
            return response(50, "Database process error", null);

        return response(0, "OK", userMapper.toDto(optionalUser.get()));
    }

    @Override
    public AuthResponse signIn(SignInRequest request) {

        if(request.getPassword() == null || request.getPassword().isBlank())
            return response(2, "Empty password", null);


        Optional<User> optionalUser = userRepository.getUser(request.getUsername(), request.getPassword());

        if(optionalUser.isEmpty())
            return response(8, "Email not found", null);

        User user = optionalUser.get();

        return response(0, "OK", userMapper.toDto(user));
    }

    private AuthResponse response(int status, String statusDesc, UserDto user) {
        return AuthResponse.builder()
                .status(status)
                .statusDesc(statusDesc)
                .user(user)
                .build();
    }


}
