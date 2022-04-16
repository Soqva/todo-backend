package com.s0qva.todobackend.mapper;

import com.s0qva.todobackend.dto.UserCreationDto;
import com.s0qva.todobackend.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserCreationMapper {
    UserCreationMapper MAPPER = Mappers.getMapper(UserCreationMapper.class);

    User toUser(UserCreationDto userCreationDto);

    @InheritInverseConfiguration
    UserCreationDto toUserCreationDto(User user);
}
