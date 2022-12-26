package ru.webapp.notaryoffice.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(String.format("Failed. %s", message));
    }
}
