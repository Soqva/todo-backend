package com.s0qva.todobackend.mapper.category;

import com.s0qva.todobackend.dto.category.CategoryIdDto;
import com.s0qva.todobackend.model.Category;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryIdMapper {
    CategoryIdMapper MAPPER = Mappers.getMapper(CategoryIdMapper.class);

    Category mapToCategory(CategoryIdDto categoryIdDto);

    @InheritInverseConfiguration
    CategoryIdDto mapToCategoryIdDto(Category category);
}
