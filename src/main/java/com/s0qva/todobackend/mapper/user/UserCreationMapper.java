package com.s0qva.todobackend.mapper.user;

import com.s0qva.todobackend.dto.user.UserCreationDto;
import com.s0qva.todobackend.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserCreationMapper {
    UserCreationMapper MAPPER = Mappers.getMapper(UserCreationMapper.class);

    User mapToUser(UserCreationDto userCreationDto);

    @InheritInverseConfiguration
    UserCreationDto mapToUserCreationDto(User user);
}
