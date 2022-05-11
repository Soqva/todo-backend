package com.s0qva.todobackend.controller;

import com.s0qva.todobackend.dto.category.CategoryCreationDto;
import com.s0qva.todobackend.dto.category.CategoryIdDto;
import com.s0qva.todobackend.dto.category.CategoryPartUpdatingDto;
import com.s0qva.todobackend.dto.category.CategoryReadingDto;
import com.s0qva.todobackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@CrossOrigin
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryReadingDto>> getAll(){
        List<CategoryReadingDto> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryReadingDto> getOne(@PathVariable Long id){
        CategoryReadingDto category = categoryService.getCategory(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody CategoryCreationDto categoryCreationDto){
        CategoryIdDto savedCategoryId = categoryService.saveCategory(categoryCreationDto);
        URI savedCategoryLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCategoryId.getId())
                .toUri();
        return ResponseEntity.created(savedCategoryLocation)
                .build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryReadingDto> patch(@PathVariable Long id,
                                                    @RequestBody CategoryPartUpdatingDto categoryPartUpdatingDto) {
        CategoryReadingDto updatedCategory = categoryService.patchCategory(id, categoryPartUpdatingDto);
        return ResponseEntity.ok(updatedCategory);
    }
}
