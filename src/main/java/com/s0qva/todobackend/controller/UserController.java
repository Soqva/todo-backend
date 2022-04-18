package com.s0qva.todobackend.controller;

import com.s0qva.todobackend.dto.user.UserReadingDto;
import com.s0qva.todobackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserReadingDto>> getAll() {
        log.info("Received request: get all users");
        List<UserReadingDto> foundUsers = userService.getAllUsers();
        log.info("Sending response status: {}", HttpStatus.OK);
        return ResponseEntity.ok(foundUsers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserReadingDto> getOneById(@PathVariable Long id) {
        log.info("Received request: get one user by its id. User's id: {}", id);
        UserReadingDto foundUser = userService.getUserById(id);
        log.info("Sending response status: {}", HttpStatus.OK);
        return ResponseEntity.ok(foundUser);
    }
}
