package com.s0qva.todobackend.mapper.user;

import com.s0qva.todobackend.dto.user.UserNameUpdatingDto;
import com.s0qva.todobackend.dto.user.UserSignUpDto;
import com.s0qva.todobackend.dto.user.UserIdDto;
import com.s0qva.todobackend.dto.user.UserReadingDto;
import com.s0qva.todobackend.model.User;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserMapper implements UserMapper {
    private final UserSignUpMapper userSignUpMapper = UserSignUpMapper.MAPPER;
    private final UserReadingMapper userReadingMapper = UserReadingMapper.MAPPER;
    private final UserIdMapper userIdMapper = UserIdMapper.MAPPER;
    private final UserNameUpdateMapper userNameUpdateMapper = UserNameUpdateMapper.MAPPER;

    @Override
    public User mapFromUserSignUpDtoToUser(UserSignUpDto userSignUpDto) {
        return userSignUpMapper.mapToUser(userSignUpDto);
    }

    @Override
    public UserReadingDto mapFromUserToUserReadingDto(User user) {
        return userReadingMapper.mapToUserReadingDto(user);
    }

    @Override
    public User mapFromUserIdDtoToUser(UserIdDto userIdDto) {
        return userIdMapper.mapToUser(userIdDto);
    }

    @Override
    public UserIdDto mapFromUserToUserIdDto(User user) {
        return userIdMapper.mapToUserIdDto(user);
    }

    @Override
    public User mapFromUserNameOnlyUpdatingDtoToUser(UserNameUpdatingDto userNameUpdatingDto) {
        return userNameUpdateMapper.mapToUser(userNameUpdatingDto);
    }
}
