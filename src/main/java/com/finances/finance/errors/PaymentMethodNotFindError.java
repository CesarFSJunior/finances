package com.finances.finance.errors;

public class PaymentMethodNotFindError extends RuntimeException{

    public PaymentMethodNotFindError(String message) {
        super(message);
    }
}
