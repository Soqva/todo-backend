package com.s0qva.todobackend.exception.handler;

import com.s0qva.todobackend.exception.NoSuchUserException;
import com.s0qva.todobackend.exception.model.IncorrectDataContainer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NoSuchUserException.class})
    public ResponseEntity<IncorrectDataContainer> handleNoSuchEntityException(RuntimeException exception) {
        log.error("NoSuchEntityException occurred. Exception message: {}", exception.getMessage());
        return buildResponseEntity(exception, HttpStatus.NOT_FOUND, "noSuchEntity");
    }

    private ResponseEntity<IncorrectDataContainer> buildResponseEntity(Exception exception,
                                                                       HttpStatus httpStatus,
                                                                       String errorKey) {
        Map<String, String> exceptions = new HashMap<>();
        exceptions.put(errorKey, exception.getMessage());
        log.error("ResponseEntity with error messages is being built");
        return new ResponseEntity<>(new IncorrectDataContainer(exceptions), httpStatus);
    }
}
