package com.s0qva.todobackend.mapper.user;

import com.s0qva.todobackend.dto.user.UserCreationDto;
import com.s0qva.todobackend.dto.user.UserIdDto;
import com.s0qva.todobackend.dto.user.UserReadingDto;
import com.s0qva.todobackend.model.User;

public interface UserMapper {
    User mapFromUserCreationDtoToUser(UserCreationDto userCreationDto);

    UserCreationDto mapFromUserToUserCreationDto(User user);

    User mapFromUserReadingDtoToUser(UserReadingDto userReadingDto);

    UserReadingDto mapFromUserToUserReadingDto(User user);

    User mapFromUserIdDtoToUser(UserIdDto userIdDto);

    UserIdDto mapFromUserToUserIdDto(User user);
}
