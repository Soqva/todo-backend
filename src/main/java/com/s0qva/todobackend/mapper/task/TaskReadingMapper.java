package com.s0qva.todobackend.mapper.task;

import com.s0qva.todobackend.dto.task.TaskReadingDto;
import com.s0qva.todobackend.mapper.category.CategoryIdMapper;
import com.s0qva.todobackend.model.Task;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CategoryIdMapper.class})
public interface TaskReadingMapper {
    TaskReadingMapper MAPPER = Mappers.getMapper(TaskReadingMapper.class);

    @Mapping(source = "taskReadingDto.categoryId", target = "category")
    Task mapToTask(TaskReadingDto taskReadingDto);

    @InheritInverseConfiguration
    @Mapping(source = "task.category", target = "categoryId")
    TaskReadingDto mapToTaskReadingDto(Task task);
}
