package org.webchat.mapper.impl;

import lombok.extern.slf4j.Slf4j;
import org.webchat.domain.User;
import org.webchat.dto.request.SignUpRequest;
import org.webchat.dto.UserDto;
import org.webchat.mapper.UserMapper;
import org.webchat.repository.UserRepo;

import java.util.Optional;

@Slf4j
public class UserMapperImpl implements UserMapper {
    private final UserRepo userRepo;

    public UserMapperImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User toEntity(SignUpRequest request) {
        Optional<User> user = userRepo.getUser(request.getUsername(), request.getPassword());
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
