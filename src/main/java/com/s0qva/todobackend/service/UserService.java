package com.s0qva.todobackend.service;

import com.s0qva.todobackend.dto.user.UserPartUpdatingDto;
import com.s0qva.todobackend.dto.user.UserReadingDto;
import com.s0qva.todobackend.exception.NoSuchUserException;
import com.s0qva.todobackend.mapper.user.UserMapper;
import com.s0qva.todobackend.model.User;
import com.s0qva.todobackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository,
                       @Qualifier("defaultUserMapper") UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserReadingDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::mapFromUserToUserReadingDto)
                .collect(Collectors.toList());
    }

    public UserReadingDto getUserById(Long id) {
        User user = getUserByIdOrElseThrow(id);
        return userMapper.mapFromUserToUserReadingDto(user);
    }

    public UserReadingDto patchUser(Long id, UserPartUpdatingDto userPartUpdatingDto) {
        User oldUser = getUserByIdOrElseThrow(id);
        User newUser = userMapper.mapFromUserPartUpdatingDtoToUser(userPartUpdatingDto);

        replaceExistingUser(oldUser, newUser);

        User updatedUser = userRepository.save(oldUser);
        return userMapper.mapFromUserToUserReadingDto(updatedUser);
    }

    private void replaceExistingUser(User oldUser, User newUser) {
        if (newUser.getUsername() != null) {
            oldUser.setUsername(newUser.getUsername());
        }
        if (newUser.getEmail() != null) {
            oldUser.setEmail(newUser.getEmail());
        }
        if (newUser.getPassword() != null) {
            oldUser.setPassword(newUser.getPassword());
        }
        if (!newUser.getCategories().isEmpty()) {
            oldUser.setCategories(newUser.getCategories());
        }
    }

    private User getUserByIdOrElseThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchUserException("there is no user with id = " + id));
    }
}
