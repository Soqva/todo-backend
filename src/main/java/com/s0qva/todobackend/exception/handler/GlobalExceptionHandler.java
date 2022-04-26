package com.s0qva.todobackend.exception.handler;

import com.s0qva.todobackend.exception.NoSuchTaskException;
import com.s0qva.todobackend.exception.NoSuchUserException;
import com.s0qva.todobackend.exception.SignInDataException;
import com.s0qva.todobackend.exception.UserAlreadyExistException;
import com.s0qva.todobackend.exception.model.IncorrectDataContainer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            NoSuchUserException.class,
            NoSuchTaskException.class
    })
    public ResponseEntity<IncorrectDataContainer> handleNoSuchEntityException(RuntimeException exception) {
        return buildResponseEntity(exception, HttpStatus.NOT_FOUND, "noSuchEntity");
    }

    @ExceptionHandler({UserAlreadyExistException.class})
    public ResponseEntity<IncorrectDataContainer> handleEntityAlreadyExistException(RuntimeException exception) {
        return buildResponseEntity(exception, HttpStatus.CONFLICT, "entityAlreadyExist");
    }

    @ExceptionHandler({SignInDataException.class})
    public ResponseEntity<IncorrectDataContainer> handleSignInException(RuntimeException exception) {
        return buildResponseEntity(exception, HttpStatus.CONFLICT, "incorrectEmailOrPassword");
    }

    private ResponseEntity<IncorrectDataContainer> buildResponseEntity(Exception exception,
                                                                       HttpStatus httpStatus,
                                                                       String errorKey) {
        Map<String, String> exceptions = new HashMap<>();
        exceptions.put(errorKey, exception.getMessage());
        return new ResponseEntity<>(new IncorrectDataContainer(exceptions), httpStatus);
    }
}
