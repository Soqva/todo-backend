package com.s0qva.todobackend.exception;

public class SignInDataException extends RuntimeException {

    public SignInDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public SignInDataException(String message) {
        super(message);
    }
}
