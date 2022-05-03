package com.s0qva.todobackend.mapper.task;

import com.s0qva.todobackend.dto.task.TaskCreationDto;
import com.s0qva.todobackend.dto.task.TaskIdDto;
import com.s0qva.todobackend.dto.task.TaskReadingDto;
import com.s0qva.todobackend.dto.task.TaskStatusUpdatingDto;
import com.s0qva.todobackend.model.Task;
import org.springframework.stereotype.Component;

@Component
public class DefaultTaskMapper implements TaskMapper {
    private final TaskCreationMapper taskCreationMapper = TaskCreationMapper.MAPPER;
    private final TaskReadingMapper taskReadingMapper = TaskReadingMapper.MAPPER;
    private final TaskIdMapper taskIdMapper = TaskIdMapper.MAPPER;
    private final TaskStatusUpdateMapper taskStatusUpdateMapper = TaskStatusUpdateMapper.MAPPER;

    @Override
    public Task mapFromTaskCreationDtoToTask(TaskCreationDto taskCreationDto) {
        return taskCreationMapper.mapToTask(taskCreationDto);
    }

    @Override
    public Task mapFromTaskReadingDtoToTask(TaskReadingDto taskReadingDto) {
        return taskReadingMapper.mapToTask(taskReadingDto);
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
    public Task mapFromTaskStatusUpdatingDtoToTask(TaskStatusUpdatingDto taskStatusUpdatingDto) {
        return taskStatusUpdateMapper.mapToTask(taskStatusUpdatingDto);
    }
}
