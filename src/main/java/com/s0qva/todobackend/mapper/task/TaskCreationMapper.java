package com.s0qva.todobackend.mapper.task;

import com.s0qva.todobackend.dto.task.TaskCreationDto;
import com.s0qva.todobackend.mapper.category.CategoryIdMapper;
import com.s0qva.todobackend.model.Task;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CategoryIdMapper.class})
public interface TaskCreationMapper {
    TaskCreationMapper MAPPER = Mappers.getMapper(TaskCreationMapper.class);

    @Mapping(source = "taskCreationDto.categoryId", target = "category")
    Task mapToTask(TaskCreationDto taskCreationDto);

    @InheritInverseConfiguration
    @Mapping(source = "task.category", target = "categoryId")
    TaskCreationDto mapToTaskCreationDto(Task task);
}
