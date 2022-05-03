package com.s0qva.todobackend.mapper.task;

import com.s0qva.todobackend.dto.task.TaskStatusUpdatingDto;
import com.s0qva.todobackend.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskStatusUpdateMapper {
    TaskStatusUpdateMapper MAPPER = Mappers.getMapper(TaskStatusUpdateMapper.class);

    Task mapToTask(TaskStatusUpdatingDto taskStatusUpdatingDto);
}
