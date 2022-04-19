package com.s0qva.todobackend.service;

import com.s0qva.todobackend.dto.user.UserReadingDto;
import com.s0qva.todobackend.exception.NoSuchUserException;
import com.s0qva.todobackend.mapper.user.UserMapper;
import com.s0qva.todobackend.model.User;
import com.s0qva.todobackend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({
        MockitoExtension.class
})
public class UserServiceTest {
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        this.userService = new UserService(userRepository, userMapper);
    }

    @Test
    void itShouldReturnAllUsersAsListOfUserReadingDto() {
        List<User> receivedUsers = new ArrayList<>();
        receivedUsers.add(new User());
        receivedUsers.add(new User());
        receivedUsers.add(new User());

        List<UserReadingDto> expectedResult = new ArrayList<>();
        expectedResult.add(new UserReadingDto());
        expectedResult.add(new UserReadingDto());
        expectedResult.add(new UserReadingDto());

        when(userRepository.findAll()).thenReturn(receivedUsers);
        when(userMapper.mapFromUserToUserReadingDto(any(User.class))).thenReturn(new UserReadingDto());

        List<UserReadingDto> actualResult = userService.getAllUsers();

        assertThat(actualResult.size()).isEqualTo(expectedResult.size());

        verify(userRepository, times(1)).findAll();
        verify(userMapper, times(expectedResult.size())).mapFromUserToUserReadingDto(any(User.class));
    }

    @Test
    void itShouldReturnUserAsUserReadingDtoByItsId() {
        Long id = 1L;
        User foundUser = new User(id, null, null, null);
        UserReadingDto expectedResult = new UserReadingDto(id, null, null);

        when(userRepository.findById(id)).thenReturn(Optional.of(foundUser));
        when(userMapper.mapFromUserToUserReadingDto(foundUser)).thenReturn(expectedResult);

        UserReadingDto actualResult = userService.getUserById(id);

        assertThat(actualResult).isEqualTo(expectedResult)
                .hasFieldOrPropertyWithValue("id", id);

        verify(userRepository, times(1)).findById(id);
        verify(userMapper, times(1)).mapFromUserToUserReadingDto(foundUser);
    }

    @Test
    void itShouldThrowNoSuchUserExceptionWhenUserWithReceivedIdDoNotExist() {
        Long id = 1L;
        String expectedExceptionMessage = "There is no user with id = " + id;

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        NoSuchUserException actualException = assertThrows(NoSuchUserException.class,
                () -> userService.getUserById(id));

        assertThat(actualException)
                .isInstanceOf(NoSuchUserException.class)
                .hasMessage(expectedExceptionMessage);

        verify(userRepository, times(1)).findById(id);
    }
}
