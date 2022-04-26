package com.s0qva.todobackend.mapper.task;

import com.s0qva.todobackend.dto.task.TaskIdDto;
import com.s0qva.todobackend.model.Task;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskIdMapper {
    TaskIdMapper MAPPER = Mappers.getMapper(TaskIdMapper.class);

    Task mapToTask(TaskIdDto taskIdDto);

    @InheritInverseConfiguration
    TaskIdDto mapToTaskIdDto(Task task);
}
