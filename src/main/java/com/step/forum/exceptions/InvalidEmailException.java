package com.step.forum.exceptions;

public class InvalidEmailException extends Exception {
    private String message;

    public InvalidEmailException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
