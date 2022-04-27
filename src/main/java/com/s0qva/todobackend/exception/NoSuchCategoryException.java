package com.s0qva.todobackend.exception;

public class NoSuchCategoryException extends RuntimeException{
    
    public NoSuchCategoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchCategoryException(String message) {
        super(message);
    }
}
