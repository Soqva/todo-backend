package com.s0qva.todobackend.mapper.user;

import com.s0qva.todobackend.dto.user.UserIdDto;
import com.s0qva.todobackend.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserIdMapper {
    UserIdMapper MAPPER = Mappers.getMapper(UserIdMapper.class);

    User mapToUser(UserIdDto userIdDto);

    @InheritInverseConfiguration
    UserIdDto mapToUserIdDto(User user);
}
