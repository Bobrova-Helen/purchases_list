package com.app.purchases.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchPurchaseItemException extends RuntimeException {
    public NoSuchPurchaseItemException() {
        super();
    }

    public NoSuchPurchaseItemException(String message) {
        super(message);
    }
}
