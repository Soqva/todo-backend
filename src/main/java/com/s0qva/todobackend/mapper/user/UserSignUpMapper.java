package com.s0qva.todobackend.mapper.user;

import com.s0qva.todobackend.dto.user.UserSignUpDto;
import com.s0qva.todobackend.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserSignUpMapper {
    UserSignUpMapper MAPPER = Mappers.getMapper(UserSignUpMapper.class);

    User mapToUser(UserSignUpDto userSignUpDto);

    @InheritInverseConfiguration
    UserSignUpDto mapToUserSignUpDto(User user);
}
