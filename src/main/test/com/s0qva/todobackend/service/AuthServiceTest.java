package com.s0qva.todobackend.service;

import com.s0qva.todobackend.dto.user.UserIdDto;
import com.s0qva.todobackend.dto.user.UserReadingDto;
import com.s0qva.todobackend.dto.user.UserSignInDto;
import com.s0qva.todobackend.dto.user.UserSignUpDto;
import com.s0qva.todobackend.exception.SignInDataException;
import com.s0qva.todobackend.exception.UserAlreadyExistException;
import com.s0qva.todobackend.mapper.user.UserMapper;
import com.s0qva.todobackend.model.User;
import com.s0qva.todobackend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({
        MockitoExtension.class
})
public class AuthServiceTest {
    private AuthService authService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        this.authService = new AuthService(userRepository, userMapper);
    }

    @Test
    void itShouldReturnUserIdDtoWhenSignUpSuccessfullyCompleted() {
        Long id = 1L;
        String email = "email@email.com";
        String password = "password";
        UserSignUpDto receivedSignUpDto = new UserSignUpDto(email, password);
        User userBeforeSave = new User(null, email, password, null, null);
        User userAfterSave = new User(id, email, password, null, null);
        UserIdDto expectResult = new UserIdDto(id);

        when(userRepository.findUserByEmail(email)).thenReturn(Optional.empty());
        when(userMapper.mapFromUserSignUpDtoToUser(receivedSignUpDto)).thenReturn(userBeforeSave);
        when(userRepository.save(userBeforeSave)).thenReturn(userAfterSave);
        when(userMapper.mapFromUserToUserIdDto(userAfterSave)).thenReturn(expectResult);

        UserIdDto actualResult = authService.signUp(receivedSignUpDto);

        assertThat(actualResult).isEqualTo(expectResult);

        verify(userRepository, times(1)).findUserByEmail(email);
        verify(userRepository, times(1)).save(userBeforeSave);
        verify(userMapper, times(1)).mapFromUserSignUpDtoToUser(receivedSignUpDto);
        verify(userMapper, times(1)).mapFromUserToUserIdDto(userAfterSave);
    }

    @Test
    void itShouldThrowUserAlreadyExistExceptionWhenUserWithReceivedEmailAlreadyExistDuringSignUp() {
        String email = "email@email.com";
        String password = "password";
        String expectedExceptionMessage = "User with email = " + email + " already exist";
        UserSignUpDto receivedSignUpDto = new UserSignUpDto(email, password);
        User existingUser = new User(null, email, password, null, null);

        when(userRepository.findUserByEmail(email)).thenReturn(Optional.of(existingUser));

        UserAlreadyExistException actualException = assertThrows(UserAlreadyExistException.class,
                () -> authService.signUp(receivedSignUpDto));

        assertThat(actualException)
                .isInstanceOf(UserAlreadyExistException.class)
                .hasMessage(expectedExceptionMessage);

        verify(userRepository, times(1)).findUserByEmail(email);
    }

    @Test
    void itShouldReturnUserReadingDtoWhenSignInSuccessfullyCompleted() {
        Long id = 1L;
        String email = "email@email.com";
        String password = "password";
        UserSignInDto receivedSignInDto = new UserSignInDto(email, password);
        User user = new User(id, email, password, null, null);
        UserReadingDto expectedResult = new UserReadingDto(id, email, null, null);

        when(userRepository.findUserByEmailAndPassword(email, password)).thenReturn(Optional.of(user));
        when(userMapper.mapFromUserToUserReadingDto(user)).thenReturn(expectedResult);

        UserReadingDto actualResult = authService.signIn(receivedSignInDto);

        assertThat(actualResult).isEqualTo(expectedResult);

        verify(userRepository, times(1)).findUserByEmailAndPassword(email, password);
        verify(userMapper, times(1)).mapFromUserToUserReadingDto(user);
    }

    @Test
    void itShouldThrowSignInDataExceptionWhenUserWithReceivedEmailAndPasswordDoesNotExist() {
        String email = "email@email.com";
        String password = "password";
        String expectedExceptionMessage = "Email or password is incorrect";
        UserSignInDto receivedSignInDto = new UserSignInDto(email, password);

        when(userRepository.findUserByEmailAndPassword(email, password)).thenReturn(Optional.empty());

        SignInDataException actualException = assertThrows(SignInDataException.class,
                () -> authService.signIn(receivedSignInDto));

        assertThat(actualException)
                .isInstanceOf(SignInDataException.class)
                .hasMessage(expectedExceptionMessage);

        verify(userRepository, times(1)).findUserByEmailAndPassword(email, password);
    }
}
