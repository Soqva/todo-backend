package com.s0qva.todobackend.mapper.user;

import com.s0qva.todobackend.dto.user.UserNameOnlyUpdatingDto;
import com.s0qva.todobackend.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserNameUpdateMapper {
    UserNameUpdateMapper MAPPER = Mappers.getMapper(UserNameUpdateMapper.class);

    User mapToUser(UserNameOnlyUpdatingDto userNameOnlyUpdatingDto);

    @InheritInverseConfiguration
    UserNameOnlyUpdatingDto mapToUserNameOnlyUpdationDto(User user);
}
