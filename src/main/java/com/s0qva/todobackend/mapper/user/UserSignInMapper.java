package com.s0qva.todobackend.mapper.user;

import com.s0qva.todobackend.dto.user.UserSignInDto;
import com.s0qva.todobackend.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserSignInMapper {
    UserSignInMapper MAPPER = Mappers.getMapper(UserSignInMapper.class);

    User mapToUser(UserSignInDto userSignInDto);

    @InheritInverseConfiguration
    UserSignInDto mapToUserSignInDto(User user);
}
