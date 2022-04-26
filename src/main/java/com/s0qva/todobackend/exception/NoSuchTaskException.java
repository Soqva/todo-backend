package com.s0qva.todobackend.exception;

public class NoSuchTaskException extends RuntimeException {

    public NoSuchTaskException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchTaskException(String message) {
        super(message);
    }
}
