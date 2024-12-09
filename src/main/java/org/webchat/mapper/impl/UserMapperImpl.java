package org.webchat.mapper.impl;

import lombok.extern.slf4j.Slf4j;
import org.webchat.domain.User;
import org.webchat.dto.SignUpRequest;
import org.webchat.dto.UserDto;
import org.webchat.mapper.UserMapper;

@Slf4j
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(SignUpRequest request) {
        return User.builder()
                .username(request.getUsername())
                .build();
    }

    @Override
    public UserDto toDto(User entity) {
        return UserDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .build();
    }
}
