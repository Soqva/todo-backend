package com.s0qva.todobackend.dto.task;

import com.s0qva.todobackend.model.enumeration.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskPartUpdatingDto {
    private LocalDate endDate;
    private String title;
    private String description;
    private TaskStatus status;
}
