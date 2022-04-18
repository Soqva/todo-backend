package com.s0qva.todobackend.service;

import com.s0qva.todobackend.dto.user.UserReadingDto;
import com.s0qva.todobackend.exception.NoSuchUserException;
import com.s0qva.todobackend.mapper.user.UserMapper;
import com.s0qva.todobackend.model.User;
import com.s0qva.todobackend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, @Qualifier("defaultUserMapper") UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserReadingDto> getAllUsers() {
        log.info("Users from the database are being received");
        List<User> users = userRepository.findAll();
        log.info("The users were got. Size of the list of the users: {}", users.size());
        log.info("Mapping each user to UserReadingDto. Collect them to a List of UserReadingDto");
        return users.stream()
                .map(userMapper::mapFromUserToUserReadingDto)
                .collect(Collectors.toList());
    }

    public UserReadingDto getUserById(Long id) {
        log.info("User with id {} is being received", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchUserException("There is no user with id = " + id));
        log.info("User was got. Found user: {}", user.getEmail());
        log.info("Mapping the user to UserReadingDto");
        return userMapper.mapFromUserToUserReadingDto(user);
    }
}
