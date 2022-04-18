package com.s0qva.todobackend.controller;

import com.s0qva.todobackend.dto.user.UserSignUpDto;
import com.s0qva.todobackend.dto.user.UserIdDto;
import com.s0qva.todobackend.dto.user.UserReadingDto;
import com.s0qva.todobackend.dto.user.UserSignInDto;
import com.s0qva.todobackend.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@RequestBody UserSignUpDto userSignUpDto) {
        log.info("Received request: sign up. Received user's email: {}", userSignUpDto.getEmail());
        UserIdDto savedUserId = authService.signUp(userSignUpDto);
        URI savedUserLocation = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/users/{id}")
                .buildAndExpand(savedUserId.getId())
                .toUri();
        log.info("Sending response status: {}. Saved user's location: {}", HttpStatus.CREATED, savedUserLocation.getPath());
        return ResponseEntity.created(savedUserLocation)
                .build();
    }

    @PostMapping("/sign-in")
    public ResponseEntity<UserReadingDto> signIn(@RequestBody UserSignInDto userSignInDto) {
        log.info("Received request: sign in. Received user's email: {}", userSignInDto.getEmail());
        UserReadingDto foundUser = authService.signIn(userSignInDto);
        log.info("Sending response status: {}", HttpStatus.OK);
        return ResponseEntity.ok(foundUser);
    }
}
