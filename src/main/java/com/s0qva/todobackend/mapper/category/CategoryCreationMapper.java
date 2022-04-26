package com.s0qva.todobackend.mapper.category;

import com.s0qva.todobackend.dto.category.CategoryCreationDto;
import com.s0qva.todobackend.mapper.user.UserIdMapper;
import com.s0qva.todobackend.model.Category;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UserIdMapper.class})
public interface CategoryCreationMapper {
    CategoryCreationMapper MAPPER = Mappers.getMapper(CategoryCreationMapper.class);

    @Mapping(source = "categoryCreationDto.userId", target = "user")
    Category mapToCategory(CategoryCreationDto categoryCreationDto);

    @InheritInverseConfiguration
    @Mapping(source = "category.user", target = "userId")
    CategoryCreationDto mapToCategoryCreationDto(Category category);
}
