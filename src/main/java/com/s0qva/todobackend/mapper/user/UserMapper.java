package com.s0qva.todobackend.mapper.user;

import com.s0qva.todobackend.dto.user.UserPartUpdatingDto;
import com.s0qva.todobackend.dto.user.UserSignUpDto;
import com.s0qva.todobackend.dto.user.UserIdDto;
import com.s0qva.todobackend.dto.user.UserReadingDto;
import com.s0qva.todobackend.model.User;

public interface UserMapper {
    User mapFromUserSignUpDtoToUser(UserSignUpDto userSignUpDto);

    UserReadingDto mapFromUserToUserReadingDto(User user);

    User mapFromUserIdDtoToUser(UserIdDto userIdDto);

    UserIdDto mapFromUserToUserIdDto(User user);

    User mapFromUserPartUpdatingDtoToUser(UserPartUpdatingDto userPartUpdatingDto);
}
