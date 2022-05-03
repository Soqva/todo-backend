package com.s0qva.todobackend.mapper.category;

import com.s0qva.todobackend.dto.category.CategoryCreationDto;
import com.s0qva.todobackend.dto.category.CategoryIdDto;
import com.s0qva.todobackend.dto.category.CategoryReadingDto;
import com.s0qva.todobackend.model.Category;

public interface CategoryMapper {
    Category mapFromCategoryCreationDtoToCategory(CategoryCreationDto categoryCreationDto);

    CategoryReadingDto mapFromCategoryToCategoryReadingDto(Category category);

    Category mapFromCategoryIdDtoToCategory(CategoryIdDto categoryIdDto);

    CategoryIdDto mapFromCategoryToCategoryIdDto(Category category);
}
