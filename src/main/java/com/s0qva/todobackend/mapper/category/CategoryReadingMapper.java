package com.s0qva.todobackend.mapper.category;

import com.s0qva.todobackend.dto.category.CategoryReadingDto;
import com.s0qva.todobackend.mapper.task.TaskReadingMapper;
import com.s0qva.todobackend.mapper.user.UserIdMapper;
import com.s0qva.todobackend.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UserIdMapper.class, TaskReadingMapper.class})
public interface CategoryReadingMapper {
    CategoryReadingMapper MAPPER = Mappers.getMapper(CategoryReadingMapper.class);

    @Mapping(source = "category.user", target = "userId")
    CategoryReadingDto mapToCategoryReadingDto(Category category);
}
