package com.s0qva.todobackend.mapper.task;

import com.s0qva.todobackend.dto.task.TaskCreationDto;
import com.s0qva.todobackend.dto.task.TaskIdDto;
import com.s0qva.todobackend.dto.task.TaskPartUpdatingDto;
import com.s0qva.todobackend.dto.task.TaskReadingDto;
import com.s0qva.todobackend.model.Task;

public interface TaskMapper {
    Task mapFromTaskCreationDtoToTask(TaskCreationDto taskCreationDto);

    TaskReadingDto mapFromTaskToTaskReadingDto(Task task);

    Task mapFromTaskIdDtoToTask(TaskIdDto taskIdDto);

    TaskIdDto mapFromTaskToTaskIdDto(Task task);

    Task mapFromTaskPartUpdatingDtoToTask(TaskPartUpdatingDto taskPartUpdatingDto);
}
