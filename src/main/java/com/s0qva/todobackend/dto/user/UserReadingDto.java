package com.s0qva.todobackend.dto.user;

import com.s0qva.todobackend.dto.category.CategoryReadingDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserReadingDto {
    private Long id;
    private String email;
    private String username;
    private List<CategoryReadingDto> categories;
}
