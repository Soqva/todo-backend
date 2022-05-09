package com.s0qva.todobackend.mapper.user;

import com.s0qva.todobackend.dto.user.UserPartUpdatingDto;
import com.s0qva.todobackend.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserPartUpdatingMapper {
    UserPartUpdatingMapper MAPPER = Mappers.getMapper(UserPartUpdatingMapper.class);

    User mapToUser(UserPartUpdatingDto userPartUpdatingDto);
}
