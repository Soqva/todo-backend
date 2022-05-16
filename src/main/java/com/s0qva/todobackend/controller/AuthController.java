package com.s0qva.todobackend.controller;

import com.s0qva.todobackend.dto.user.UserSignUpDto;
import com.s0qva.todobackend.dto.user.UserIdDto;
import com.s0qva.todobackend.dto.user.UserReadingDto;
import com.s0qva.todobackend.dto.user.UserSignInDto;
import com.s0qva.todobackend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@Valid @RequestBody UserSignUpDto userSignUpDto) {
        UserIdDto savedUserId = authService.signUp(userSignUpDto);
        URI savedUserLocation = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/users/{id}")
                .buildAndExpand(savedUserId.getId())
                .toUri();
        return ResponseEntity.created(savedUserLocation)
                .build();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<UserReadingDto> signIn(@RequestBody UserSignInDto userSignInDto) {
        UserReadingDto foundUser = authService.signIn(userSignInDto);
        return ResponseEntity.ok(foundUser);
    }
}
