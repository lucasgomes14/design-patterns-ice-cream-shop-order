package com.iceCreamShop.DesignPatterns.exception;

public class OrderStateException extends UnsupportedIceCreamTypeException {
    public OrderStateException() {
        super("This order cannot be cancelled.");
    }

    public OrderStateException(String message) {
        super(message);
    }
}
