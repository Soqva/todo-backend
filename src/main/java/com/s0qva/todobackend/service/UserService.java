package com.s0qva.todobackend.service;

import com.s0qva.todobackend.dto.UserCreationDto;
import com.s0qva.todobackend.dto.UserIdDto;
import com.s0qva.todobackend.dto.UserReadingDto;
import com.s0qva.todobackend.mapper.UserCreationMapper;
import com.s0qva.todobackend.mapper.UserIdMapper;
import com.s0qva.todobackend.mapper.UserReadingMapper;
import com.s0qva.todobackend.model.User;
import com.s0qva.todobackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserCreationMapper userCreationMapper = UserCreationMapper.MAPPER;
    private final UserReadingMapper userReadingMapper = UserReadingMapper.MAPPER;
    private final UserIdMapper userIdMapper = UserIdMapper.MAPPER;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserReadingDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userReadingMapper::toUserReadingDto)
                .collect(Collectors.toList());
    }

    public UserReadingDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElse(new User());
        return userReadingMapper.toUserReadingDto(user);
    }

    public UserIdDto saveUser(UserCreationDto userCreationDto) {
        User user = userCreationMapper.toUser(userCreationDto);
        User savedUser = userRepository.save(user);
        return userIdMapper.toUserIdDto(savedUser);
    }
}
