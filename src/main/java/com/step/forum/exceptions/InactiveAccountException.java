package com.step.forum.exceptions;

public class InactiveAccountException extends Exception {
    private String message;

    public InactiveAccountException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
