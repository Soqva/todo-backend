package com.s0qva.todobackend.exception.handler;

import com.s0qva.todobackend.exception.NoSuchCategoryException;
import com.s0qva.todobackend.exception.NoSuchTaskException;
import com.s0qva.todobackend.exception.NoSuchUserException;
import com.s0qva.todobackend.exception.SignInDataException;
import com.s0qva.todobackend.exception.UserAlreadyExistException;
import com.s0qva.todobackend.exception.model.IncorrectDataContainer;
import com.s0qva.todobackend.util.RegexUtil;
import com.sun.istack.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
        return ResponseEntityFactory.buildResponseEntity(exception, HttpStatus.NOT_FOUND, "noSuchEntity");
    }

    @ExceptionHandler({UserAlreadyExistException.class})
    public ResponseEntity<IncorrectDataContainer> handleEntityAlreadyExist(RuntimeException exception) {
        return ResponseEntityFactory.buildResponseEntity(exception, HttpStatus.CONFLICT, "entityAlreadyExist");
    }

    @ExceptionHandler({SignInDataException.class})
    public ResponseEntity<IncorrectDataContainer> handleSignIn(RuntimeException exception) {
        return ResponseEntityFactory.buildResponseEntity(exception, HttpStatus.CONFLICT, "incorrectEmailOrPassword");
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<IncorrectDataContainer> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        return ResponseEntityFactory.buildResponseEntity(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<IncorrectDataContainer> handleHttpMessageNotReadable(HttpMessageNotReadableException exception) {
        String exceptionMessage = RegexUtil.findReceivedDate(exception) + " is not a valid date";
        return ResponseEntityFactory.buildResponseEntity(HttpStatus.BAD_REQUEST, "incorrectDateFormat", exceptionMessage);
    }

    private static class ResponseEntityFactory {

        private static ResponseEntity<IncorrectDataContainer> buildResponseEntity(Exception exception,
                                                                                  HttpStatus httpStatus) {
            return buildResponseEntity(exception, httpStatus, null);
        }

        private static ResponseEntity<IncorrectDataContainer> buildResponseEntity(Exception exception,
                                                                                  HttpStatus httpStatus,
                                                                                  String errorKey) {
            Map<String, String> errors = buildMapWithErrors(exception, errorKey);
            return new ResponseEntity<>(new IncorrectDataContainer(errors), httpStatus);
        }

        private static ResponseEntity<IncorrectDataContainer> buildResponseEntity(HttpStatus httpStatus,
                                                                                  String errorKey,
                                                                                  String exceptionMessage) {
            Map<String, String> errors = buildMapWithErrors(new RuntimeException(exceptionMessage), errorKey);
            return new ResponseEntity<>(new IncorrectDataContainer(errors), httpStatus);
        }

        private static Map<String, String> buildMapWithErrors(Exception exception, @Nullable String errorKey) {
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
}
