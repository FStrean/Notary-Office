package ru.webapp.notaryoffice.exception;

public class RoleNotFoundException extends RuntimeException{
    public RoleNotFoundException(String message) {
        super(String.format("Failed. %s", message));
    }
}
