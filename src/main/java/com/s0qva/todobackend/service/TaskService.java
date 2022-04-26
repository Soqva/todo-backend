package com.s0qva.todobackend.service;

import com.s0qva.todobackend.dto.task.TaskCreationDto;
import com.s0qva.todobackend.dto.task.TaskIdDto;
import com.s0qva.todobackend.dto.task.TaskReadingDto;
import com.s0qva.todobackend.exception.NoSuchUserException;
import com.s0qva.todobackend.mapper.task.TaskMapper;
import com.s0qva.todobackend.model.Task;
import com.s0qva.todobackend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public List<TaskReadingDto> getAllTasks(){
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::mapFromTaskToTaskReadingDto)
                .collect(Collectors.toList());

    }

    public TaskReadingDto getTask(Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NoSuchUserException("There is no task with id = " + id));
        return taskMapper.mapFromTaskToTaskReadingDto(task);
    }

    public TaskIdDto saveTask(TaskCreationDto taskCreationDto){
        Task task = taskMapper.mapFromTaskCreationDtoToTask(taskCreationDto);
        Task savedTask = taskRepository.save(task);
        return taskMapper.mapFromTaskToTaskIdDto(savedTask);
    }
}
