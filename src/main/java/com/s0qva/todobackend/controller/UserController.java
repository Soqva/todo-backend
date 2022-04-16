package com.s0qva.todobackend.controller;

import com.s0qva.todobackend.dto.UserCreationDto;
import com.s0qva.todobackend.dto.UserIdDto;
import com.s0qva.todobackend.dto.UserReadingDto;
import com.s0qva.todobackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserReadingDto>> getAll() {
        List<UserReadingDto> foundUsers = userService.getAllUsers();
        return ResponseEntity.ok(foundUsers);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserReadingDto> getOneById(@PathVariable Long id) {
        UserReadingDto foundUser = userService.getUserById(id);
        return ResponseEntity.ok(foundUser);
    }

    @PostMapping("/users")
    public ResponseEntity<Void> save(@RequestBody UserCreationDto userCreationDto) {
        UserIdDto savedUserId = userService.saveUser(userCreationDto);
        URI savedUserLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUserId.getId())
                .toUri();
        return ResponseEntity.created(savedUserLocation)
                .build();
    }
}
