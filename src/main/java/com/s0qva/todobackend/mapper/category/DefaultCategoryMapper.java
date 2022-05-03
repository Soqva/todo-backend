package com.s0qva.todobackend.mapper.category;

import com.s0qva.todobackend.dto.category.CategoryCreationDto;
import com.s0qva.todobackend.dto.category.CategoryIdDto;
import com.s0qva.todobackend.dto.category.CategoryReadingDto;
import com.s0qva.todobackend.model.Category;
import org.springframework.stereotype.Component;

@Component
public class DefaultCategoryMapper implements CategoryMapper{
    private final CategoryCreationMapper categoryCreationMapper = CategoryCreationMapper.MAPPER;
    private final CategoryReadingMapper categoryReadingMapper = CategoryReadingMapper.MAPPER;
    private final CategoryIdMapper categoryIdMapper = CategoryIdMapper.MAPPER;


    @Override
    public Category mapFromCategoryCreationDtoToCategory(CategoryCreationDto categoryCreationDto) {
        return categoryCreationMapper.mapToCategory(categoryCreationDto);
    }

    @Override
    public CategoryReadingDto mapFromCategoryToCategoryReadingDto(Category category) {
        return categoryReadingMapper.mapToCategoryReadingDto(category);
    }

    @Override
    public Category mapFromCategoryIdDtoToCategory(CategoryIdDto categoryIdDto) {
        return categoryIdMapper.mapToCategory(categoryIdDto);
    }

    @Override
    public CategoryIdDto mapFromCategoryToCategoryIdDto(Category category) {
        return categoryIdMapper.mapToCategoryIdDto(category);
    }
}
