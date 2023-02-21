package org.example.exception;

public class ToDoNotFoundException extends RuntimeException {

    private final String message;
    public ToDoNotFoundException(String message) {
        super(message);
        this.message = message;
    }

}
