package com.step.forum.exceptions;

public class DuplicateEmailException extends Exception {
    private String message;

    public DuplicateEmailException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
