package com.s0qva.todobackend.dto.task;

import com.s0qva.todobackend.model.enumeration.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskPartUpdatingDto {
    private LocalDate endDate;

    @Size(max = 128, message = "task's title length must be less than {max}")
    private String title;

    @Size(max = 2048, message = "description length must be less than {max}")
    private String description;

    private TaskStatus status;
}
