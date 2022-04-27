package com.s0qva.todobackend.controller;

import com.s0qva.todobackend.dto.user.UserNameOnlyUpdatingDto;
import com.s0qva.todobackend.dto.user.UserReadingDto;
import com.s0qva.todobackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserReadingDto>> getAll() {
        List<UserReadingDto> foundUsers = userService.getAllUsers();
        return ResponseEntity.ok(foundUsers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserReadingDto> getOne(@PathVariable Long id) {
        UserReadingDto foundUser = userService.getUserById(id);
        return ResponseEntity.ok(foundUser);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserReadingDto> updateUsername(@PathVariable Long id,
                                                         @RequestBody UserNameOnlyUpdatingDto userNameOnlyUpdatingDto) {
        UserReadingDto updatedUser = userService.updateUsername(id, userNameOnlyUpdatingDto);
        return ResponseEntity.ok(updatedUser);
    }
}
