package org.webchat.mapper;


import org.webchat.domain.User;
import org.webchat.dto.SignUpRequest;
import org.webchat.dto.UserDto;

public interface UserMapper {

    User toEntity(SignUpRequest request);

    UserDto toDto(User entity);

}
