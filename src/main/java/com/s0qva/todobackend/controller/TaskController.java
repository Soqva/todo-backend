package com.s0qva.todobackend.controller;

import com.s0qva.todobackend.dto.category.CategoryPartUpdatingDto;
import com.s0qva.todobackend.dto.category.CategoryReadingDto;
import com.s0qva.todobackend.dto.task.TaskCreationDto;
import com.s0qva.todobackend.dto.task.TaskIdDto;
import com.s0qva.todobackend.dto.task.TaskPartUpdatingDto;
import com.s0qva.todobackend.dto.task.TaskReadingDto;
import com.s0qva.todobackend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@CrossOrigin
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskReadingDto>> getAll(){
        List<TaskReadingDto> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskReadingDto> getOne(@PathVariable Long id){
        TaskReadingDto task = taskService.getTask(id);
        return ResponseEntity.ok(task);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody TaskCreationDto taskCreationDto){
        TaskIdDto savedTaskId = taskService.saveTask(taskCreationDto);
        URI savedTaskLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedTaskId.getId())
                .toUri();
        return ResponseEntity.created(savedTaskLocation)
                .build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskReadingDto> patch(@PathVariable Long id,
                                                @RequestBody TaskPartUpdatingDto taskPartUpdatingDto) {
        TaskReadingDto updatedTask = taskService.patchTask(id, taskPartUpdatingDto);
        return ResponseEntity.ok(updatedTask);
    }
}
