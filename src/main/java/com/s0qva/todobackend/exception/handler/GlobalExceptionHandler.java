package com.s0qva.todobackend.exception.handler;

import com.s0qva.todobackend.exception.NoSuchCategoryException;
import com.s0qva.todobackend.exception.NoSuchTaskException;
import com.s0qva.todobackend.exception.NoSuchUserException;
import com.s0qva.todobackend.exception.SignInDataException;
import com.s0qva.todobackend.exception.UserAlreadyExistException;
import com.s0qva.todobackend.exception.model.IncorrectDataContainer;
import com.sun.istack.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            NoSuchUserException.class,
            NoSuchTaskException.class,
            NoSuchCategoryException.class
    })
    public ResponseEntity<IncorrectDataContainer> handleNoSuchEntity(RuntimeException exception) {
        return buildResponseEntity(exception, HttpStatus.NOT_FOUND, "noSuchEntity");
    }

    @ExceptionHandler({UserAlreadyExistException.class})
    public ResponseEntity<IncorrectDataContainer> handleEntityAlreadyExist(RuntimeException exception) {
        return buildResponseEntity(exception, HttpStatus.CONFLICT, "entityAlreadyExist");
    }

    @ExceptionHandler({SignInDataException.class})
    public ResponseEntity<IncorrectDataContainer> handleSignIn(RuntimeException exception) {
        return buildResponseEntity(exception, HttpStatus.CONFLICT, "incorrectEmailOrPassword");
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<IncorrectDataContainer> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        return buildResponseEntity(exception, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<IncorrectDataContainer> buildResponseEntity(Exception exception,
                                                                       HttpStatus httpStatus) {
        return buildResponseEntity(exception, httpStatus, null);
    }

    private ResponseEntity<IncorrectDataContainer> buildResponseEntity(Exception exception,
                                                                       HttpStatus httpStatus,
                                                                       String errorKey) {
        Map<String, String> errors = buildMapWithErrors(exception, errorKey);
        return new ResponseEntity<>(new IncorrectDataContainer(errors), httpStatus);
    }

    private Map<String, String> buildMapWithErrors(Exception exception, @Nullable String errorKey) {
        Map<String, String> errors = new HashMap<>();

        if (exception instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException methodArgumentNotValidException = (MethodArgumentNotValidException) exception;

            for (FieldError error : methodArgumentNotValidException.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return errors;
        }

        errors.put(errorKey, exception.getMessage());
        return errors;
    }

}
