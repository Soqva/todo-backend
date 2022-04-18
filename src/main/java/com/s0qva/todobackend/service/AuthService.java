package com.s0qva.todobackend.service;

import com.s0qva.todobackend.dto.user.UserSignUpDto;
import com.s0qva.todobackend.dto.user.UserIdDto;
import com.s0qva.todobackend.dto.user.UserReadingDto;
import com.s0qva.todobackend.dto.user.UserSignInDto;
import com.s0qva.todobackend.exception.SignInDataException;
import com.s0qva.todobackend.exception.UserAlreadyExistException;
import com.s0qva.todobackend.mapper.user.UserMapper;
import com.s0qva.todobackend.model.User;
import com.s0qva.todobackend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public AuthService(UserRepository userRepository, @Qualifier("defaultUserMapper") UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserIdDto signUp(UserSignUpDto userSignUpDto) {
        log.info("Checking an already existing user with email: {}", userSignUpDto.getEmail());
        userRepository.findUserByEmail(userSignUpDto.getEmail())
                .ifPresent(user -> {
                    throw new UserAlreadyExistException("User with email = " + user.getEmail() + " already exist");
                });
        log.info("Mapping the UserSignUpDto to User");
        User user = userMapper.mapFromUserSignUpDtoToUser(userSignUpDto);
        log.info("User with email {} is being saved", user.getEmail());
        User savedUser = userRepository.save(user);
        log.info("The user was saved. Saved user's id: {}", savedUser.getId());
        log.info("Mapping the User to UserIdDto");
        return userMapper.mapFromUserToUserIdDto(savedUser);
    }

    public UserReadingDto signIn(UserSignInDto userSignInDto) {
        log.info("Searching for a user by its email and its password");
        User user = userRepository.findUserByEmailAndPassword(userSignInDto.getEmail(), userSignInDto.getPassword())
                .orElseThrow(() -> new SignInDataException("Email or password is incorrect"));
        log.info("The user was found. Mapping the User to UserReadingDto");
        return userMapper.mapFromUserToUserReadingDto(user);
    }
}
