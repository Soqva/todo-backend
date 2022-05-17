package com.s0qva.todobackend.service;

import com.s0qva.todobackend.dto.task.TaskCreationDto;
import com.s0qva.todobackend.dto.task.TaskIdDto;
import com.s0qva.todobackend.dto.task.TaskPartUpdatingDto;
import com.s0qva.todobackend.dto.task.TaskReadingDto;
import com.s0qva.todobackend.exception.NoSuchTaskException;
import com.s0qva.todobackend.mapper.task.TaskMapper;
import com.s0qva.todobackend.model.Task;
import com.s0qva.todobackend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    public TaskService(TaskRepository taskRepository,
                       @Qualifier("defaultTaskMapper") TaskMapper taskMapper) {
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
        Task task = getTaskByIdOrElseThrow(id);
        return taskMapper.mapFromTaskToTaskReadingDto(task);
    }

    public TaskIdDto saveTask(TaskCreationDto taskCreationDto){
        Task task = taskMapper.mapFromTaskCreationDtoToTask(taskCreationDto);
        Task savedTask = taskRepository.save(task);
        return taskMapper.mapFromTaskToTaskIdDto(savedTask);
    }

    public TaskReadingDto patchTask(Long id, TaskPartUpdatingDto taskPartUpdatingDto){
        Task oldTask = getTaskByIdOrElseThrow(id);
        Task newTask = taskMapper.mapFromTaskPartUpdatingDtoToTask(taskPartUpdatingDto);

        replaceExistingTask(oldTask, newTask);
        Task updatedTask = taskRepository.save(oldTask);
        return taskMapper.mapFromTaskToTaskReadingDto(updatedTask);
    }

    public void deleteTaskById(Long id) {
        Task existingTask = getTaskByIdOrElseThrow(id);
        taskRepository.delete(existingTask);
    }

    private void replaceExistingTask(Task oldTask, Task newTask) {
        if (newTask.getEndDate() != null) {
            oldTask.setEndDate(newTask.getEndDate());
        }
        if (newTask.getTitle() != null) {
            oldTask.setTitle(newTask.getTitle());
        }
        if (newTask.getDescription() != null) {
            oldTask.setDescription(newTask.getDescription());
        }
        if (newTask.getStatus() != null) {
            oldTask.setStatus(newTask.getStatus());
        }
    }

    private Task getTaskByIdOrElseThrow(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new NoSuchTaskException("there is no task with id = " + id));
    }
}
