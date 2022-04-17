package com.s0qva.todobackend.mapper.user;

import com.s0qva.todobackend.dto.user.UserCreationDto;
import com.s0qva.todobackend.dto.user.UserIdDto;
import com.s0qva.todobackend.dto.user.UserReadingDto;
import com.s0qva.todobackend.model.User;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserMapper implements UserMapper {
    private final UserCreationMapper userCreationMapper = UserCreationMapper.MAPPER;
    private final UserReadingMapper userReadingMapper = UserReadingMapper.MAPPER;
    private final UserIdMapper userIdMapper = UserIdMapper.MAPPER;

    public User mapFromUserCreationDtoToUser(UserCreationDto userCreationDto) {
        return userCreationMapper.mapToUser(userCreationDto);
    }

    public UserCreationDto mapFromUserToUserCreationDto(User user) {
        return userCreationMapper.mapToUserCreationDto(user);
    }

    public User mapFromUserReadingDtoToUser(UserReadingDto userReadingDto) {
        return userReadingMapper.mapToUser(userReadingDto);
    }

    public UserReadingDto mapFromUserToUserReadingDto(User user) {
        return userReadingMapper.mapToUserReadingDto(user);
    }

    public User mapFromUserIdDtoToUser(UserIdDto userIdDto) {
        return userIdMapper.mapToUser(userIdDto);
    }

    public UserIdDto mapFromUserToUserIdDto(User user) {
        return userIdMapper.mapToUserIdDto(user);
    }
}
