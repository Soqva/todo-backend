package com.s0qva.todobackend.mapper.user;

import com.s0qva.todobackend.dto.user.UserReadingDto;
import com.s0qva.todobackend.mapper.category.CategoryReadingMapper;
import com.s0qva.todobackend.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CategoryReadingMapper.class})
public interface UserReadingMapper {
    UserReadingMapper MAPPER = Mappers.getMapper(UserReadingMapper.class);

    User mapToUser(UserReadingDto userReadingDto);

    @InheritInverseConfiguration
    UserReadingDto mapToUserReadingDto(User user);
}
