package com.s0qva.todobackend.dto.category;

import com.s0qva.todobackend.dto.task.TaskReadingDto;
import com.s0qva.todobackend.dto.user.UserIdDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryReadingDto {
    private Long id;
    private String title;
    private UserIdDto userId;
    private List<TaskReadingDto> tasks;
}
