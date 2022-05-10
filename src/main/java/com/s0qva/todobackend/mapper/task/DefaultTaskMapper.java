package com.s0qva.todobackend.mapper.task;

import com.s0qva.todobackend.dto.task.TaskCreationDto;
import com.s0qva.todobackend.dto.task.TaskIdDto;
import com.s0qva.todobackend.dto.task.TaskPartUpdatingDto;
import com.s0qva.todobackend.dto.task.TaskReadingDto;
import com.s0qva.todobackend.model.Task;
import org.springframework.stereotype.Component;

@Component
public class DefaultTaskMapper implements TaskMapper {
    private final TaskCreationMapper taskCreationMapper = TaskCreationMapper.MAPPER;
    private final TaskReadingMapper taskReadingMapper = TaskReadingMapper.MAPPER;
    private final TaskIdMapper taskIdMapper = TaskIdMapper.MAPPER;
    private final TaskPartUpdatingMapper taskPartUpdatingMapper = TaskPartUpdatingMapper.MAPPER;

    @Override
    public Task mapFromTaskCreationDtoToTask(TaskCreationDto taskCreationDto) {
        return taskCreationMapper.mapToTask(taskCreationDto);
    }

    @Override
    public TaskReadingDto mapFromTaskToTaskReadingDto(Task task) {
        return taskReadingMapper.mapToTaskReadingDto(task);
    }

    @Override
    public Task mapFromTaskIdDtoToTask(TaskIdDto taskIdDto) {
        return taskIdMapper.mapToTask(taskIdDto);
    }

    @Override
    public TaskIdDto mapFromTaskToTaskIdDto(Task task) {
        return taskIdMapper.mapToTaskIdDto(task);
    }

    @Override
    public Task mapFromTaskPartUpdatingDtoToTask(TaskPartUpdatingDto taskPartUpdatingDto) {
        return taskPartUpdatingMapper.mapToTask(taskPartUpdatingDto);
    }
}
