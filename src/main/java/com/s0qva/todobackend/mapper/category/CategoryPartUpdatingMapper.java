package com.s0qva.todobackend.mapper.category;

import com.s0qva.todobackend.dto.category.CategoryPartUpdatingDto;
import com.s0qva.todobackend.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryPartUpdatingMapper {
    CategoryPartUpdatingMapper MAPPER = Mappers.getMapper(CategoryPartUpdatingMapper.class);

    Category mapToCategory(CategoryPartUpdatingDto categoryPartUpdatingDto);
}
