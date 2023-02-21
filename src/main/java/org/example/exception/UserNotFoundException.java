package org.example.exception;

public class UserNotFoundException extends RuntimeException{

    private final String message;

    public UserNotFoundException(String message) {
        super(message);
        this.message = message;
    }

}
