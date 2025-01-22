package com.finances.finance.errors;

public class UserCreationError extends RuntimeException {

    public UserCreationError(String message) {
        super(message);
    }
}
