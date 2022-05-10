package com.s0qva.todobackend.mapper.task;

import com.s0qva.todobackend.dto.task.TaskPartUpdatingDto;
import com.s0qva.todobackend.model.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskPartUpdatingMapper {
    TaskPartUpdatingMapper MAPPER = Mappers.getMapper(TaskPartUpdatingMapper.class);

    Task mapToTask(TaskPartUpdatingDto taskPartUpdatingDto);
}
