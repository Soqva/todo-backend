package com.s0qva.todobackend.mapper.user;

import com.s0qva.todobackend.dto.user.UserSignInDto;
import com.s0qva.todobackend.dto.user.UserSignUpDto;
import com.s0qva.todobackend.dto.user.UserIdDto;
import com.s0qva.todobackend.dto.user.UserReadingDto;
import com.s0qva.todobackend.model.User;

public interface UserMapper {
    User mapFromUserSignUpDtoToUser(UserSignUpDto userSignUpDto);

    UserSignUpDto mapFromUserToUserSignUpDto(User user);

    User mapFromUserSignInDtoToUser(UserSignInDto userSignInDto);

    UserSignInDto mapFromUserToUserSignInDto(User user);

    User mapFromUserReadingDtoToUser(UserReadingDto userReadingDto);

    UserReadingDto mapFromUserToUserReadingDto(User user);

    User mapFromUserIdDtoToUser(UserIdDto userIdDto);

    UserIdDto mapFromUserToUserIdDto(User user);
}
