package com.s0qva.todobackend.service;

import com.s0qva.todobackend.dto.category.CategoryCreationDto;
import com.s0qva.todobackend.dto.category.CategoryIdDto;
import com.s0qva.todobackend.dto.category.CategoryReadingDto;
import com.s0qva.todobackend.exception.NoSuchCategoryException;
import com.s0qva.todobackend.mapper.category.CategoryMapper;
import com.s0qva.todobackend.model.Category;
import com.s0qva.todobackend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository,
                           @Qualifier("defaultCategoryMapper") CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryReadingDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::mapFromCategoryToCategoryReadingDto)
                .collect(Collectors.toList());

    }

    public CategoryReadingDto getCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchCategoryException("There is no category with id = " + id));
        return categoryMapper.mapFromCategoryToCategoryReadingDto(category);
    }

    public CategoryIdDto saveCategory(CategoryCreationDto categoryCreationDto) {
        Category category = categoryMapper.mapFromCategoryCreationDtoToCategory(categoryCreationDto);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.mapFromCategoryToCategoryIdDto(savedCategory);
    }
}
