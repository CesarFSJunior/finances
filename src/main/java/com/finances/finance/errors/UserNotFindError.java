package com.finances.finance.errors;

public class UserNotFindError extends RuntimeException {

    public UserNotFindError(String message) {
        super(message);
    }
}
