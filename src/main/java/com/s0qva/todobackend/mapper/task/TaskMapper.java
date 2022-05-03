package com.s0qva.todobackend.mapper.task;

import com.s0qva.todobackend.dto.task.TaskCreationDto;
import com.s0qva.todobackend.dto.task.TaskIdDto;
import com.s0qva.todobackend.dto.task.TaskReadingDto;
import com.s0qva.todobackend.dto.task.TaskStatusUpdatingDto;
import com.s0qva.todobackend.model.Task;

public interface TaskMapper {
    Task mapFromTaskCreationDtoToTask(TaskCreationDto taskCreationDto);

    Task mapFromTaskReadingDtoToTask(TaskReadingDto taskReadingDto);

    TaskReadingDto mapFromTaskToTaskReadingDto(Task task);

    Task mapFromTaskIdDtoToTask(TaskIdDto taskIdDto);

    TaskIdDto mapFromTaskToTaskIdDto(Task task);

    Task mapFromTaskStatusUpdatingDtoToTask(TaskStatusUpdatingDto taskStatusUpdatingDto);
}
