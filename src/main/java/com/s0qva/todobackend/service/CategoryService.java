package com.s0qva.todobackend.service;

import com.s0qva.todobackend.dto.category.CategoryCreationDto;
import com.s0qva.todobackend.dto.category.CategoryIdDto;
import com.s0qva.todobackend.dto.category.CategoryPartUpdatingDto;
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
        Category category = getCategoryByIdOrElseThrow(id);
        return categoryMapper.mapFromCategoryToCategoryReadingDto(category);
    }

    public CategoryIdDto saveCategory(CategoryCreationDto categoryCreationDto) {
        Category category = categoryMapper.mapFromCategoryCreationDtoToCategory(categoryCreationDto);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.mapFromCategoryToCategoryIdDto(savedCategory);
    }

    public CategoryReadingDto patchCategory(Long id, CategoryPartUpdatingDto categoryPartUpdatingDto) {
        Category oldCategory = getCategoryByIdOrElseThrow(id);
        Category newCategory = categoryMapper.mapFromCategoryPartUpdatingDtoToCategory(categoryPartUpdatingDto);

        replaceExistingCategory(oldCategory, newCategory);

        Category updatedCategory = categoryRepository.save(oldCategory);
        return categoryMapper.mapFromCategoryToCategoryReadingDto(updatedCategory);
    }

    public void deleteCategoryById(Long id) {
        Category existingCategory = getCategoryByIdOrElseThrow(id);
        categoryRepository.delete(existingCategory);
    }

    private void replaceExistingCategory(Category oldCategory, Category newCategory) {
        if (newCategory.getTitle() != null) {
            oldCategory.setTitle(newCategory.getTitle());
        }
    }

    private Category getCategoryByIdOrElseThrow(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchCategoryException("there is no category with id = " + id));
    }
}
