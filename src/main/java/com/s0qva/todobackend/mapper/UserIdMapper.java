package com.s0qva.todobackend.mapper;

import com.s0qva.todobackend.dto.UserIdDto;
import com.s0qva.todobackend.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserIdMapper {
    UserIdMapper MAPPER = Mappers.getMapper(UserIdMapper.class);

    User toUser(UserIdMapper userIdMapper);

    @InheritInverseConfiguration
    UserIdDto toUserIdDto(User user);
}
