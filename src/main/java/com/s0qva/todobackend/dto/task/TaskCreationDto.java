package com.s0qva.todobackend.dto.task;

import com.s0qva.todobackend.dto.category.CategoryIdDto;
import com.s0qva.todobackend.model.enumeration.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskCreationDto {
    @NotNull(message = "end date must not be null")
    private LocalDate endDate;

    @NotBlank(message = "title must not be blank")
    @Size(max = 128, message = "task's title length must be less than {max}")
    private String title;

    @Size(max = 2048, message = "description length must be less than {max}")
    private String description;

    @NotNull(message = "status must not be null")
    private TaskStatus status;

    @Valid
    @NotNull(message = "category's id must not be null")
    private CategoryIdDto categoryId;
}
