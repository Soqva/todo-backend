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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public AuthService(UserRepository userRepository,
                       @Qualifier("defaultUserMapper") UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserIdDto signUp(UserSignUpDto userSignUpDto) {
        userRepository.findUserByEmail(userSignUpDto.getEmail())
                .ifPresent(user -> {
                    throw new UserAlreadyExistException("user with email = " + user.getEmail() + " already exist");
                });
        User user = userMapper.mapFromUserSignUpDtoToUser(userSignUpDto);
        User savedUser = userRepository.save(user);
        return userMapper.mapFromUserToUserIdDto(savedUser);
    }

    public UserReadingDto signIn(UserSignInDto userSignInDto) {
        User user = userRepository.findUserByEmailAndPassword(userSignInDto.getEmail(), userSignInDto.getPassword())
                .orElseThrow(() -> new SignInDataException("email or password is incorrect"));
        return userMapper.mapFromUserToUserReadingDto(user);
    }
}
