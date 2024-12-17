package org.webchat.mapper.impl;

import lombok.extern.slf4j.Slf4j;
import org.webchat.domain.User;
import org.webchat.dto.request.SignUpRequest;
import org.webchat.dto.UserDto;
import org.webchat.mapper.UserMapper;
import org.webchat.usecase.Root;

import java.util.Optional;

@Slf4j
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(SignUpRequest request) {
        Optional<User> user = Root.usersRepo.getUser(request.getUsername(), request.getPassword());
        return user.orElse(null);
    }

    @Override
    public UserDto toDto(User entity) {
        return UserDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .build();
    }
}
