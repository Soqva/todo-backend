package com.s0qva.todobackend.dto.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryPartUpdatingDto {
    @Size(max = 128, message = "title must be less than {max}")
    private String title;
}
