package com.s0qva.todobackend.mapper;

import com.s0qva.todobackend.dto.UserReadingDto;
import com.s0qva.todobackend.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserReadingMapper {
    UserReadingMapper MAPPER = Mappers.getMapper(UserReadingMapper.class);

    User toUser(UserReadingDto userReadingDto);

    @InheritInverseConfiguration
    UserReadingDto toUserReadingDto(User user);
}
