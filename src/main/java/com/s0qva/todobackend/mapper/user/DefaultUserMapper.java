package com.s0qva.todobackend.mapper.user;

import com.s0qva.todobackend.dto.user.UserNameOnlyUpdatingDto;
import com.s0qva.todobackend.dto.user.UserSignInDto;
import com.s0qva.todobackend.dto.user.UserSignUpDto;
import com.s0qva.todobackend.dto.user.UserIdDto;
import com.s0qva.todobackend.dto.user.UserReadingDto;
import com.s0qva.todobackend.model.User;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserMapper implements UserMapper {
    private final UserSignUpMapper userSignUpMapper = UserSignUpMapper.MAPPER;
    private final UserSignInMapper userSignInMapper = UserSignInMapper.MAPPER;
    private final UserReadingMapper userReadingMapper = UserReadingMapper.MAPPER;
    private final UserIdMapper userIdMapper = UserIdMapper.MAPPER;
    private final UserNameUpdateMapper userNameUpdateMapper = UserNameUpdateMapper.MAPPER;

    @Override
    public User mapFromUserSignUpDtoToUser(UserSignUpDto userSignUpDto) {
        return userSignUpMapper.mapToUser(userSignUpDto);
    }

    @Override
    public UserSignUpDto mapFromUserToUserSignUpDto(User user) {
        return userSignUpMapper.mapToUserSignUpDto(user);
    }

    @Override
    public User mapFromUserSignInDtoToUser(UserSignInDto userSignInDto) {
        return userSignInMapper.mapToUser(userSignInDto);
    }

    @Override
    public UserSignInDto mapFromUserToUserSignInDto(User user) {
        return userSignInMapper.mapToUserSignInDto(user);
    }

    @Override
    public User mapFromUserReadingDtoToUser(UserReadingDto userReadingDto) {
        return userReadingMapper.mapToUser(userReadingDto);
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
    public User mapFromUserNameOnlyUpdatingDtoToUser(UserNameOnlyUpdatingDto userNameOnlyUpdatingDto) {
        return userNameUpdateMapper.mapToUser(userNameOnlyUpdatingDto);
    }
}
