package com.iceCreamShop.DesignPatterns.exception;

public class IceCreamTypeNotSupportedException extends UnsupportedIceCreamTypeException {

    public IceCreamTypeNotSupportedException() {
        super("Ice cream type not supported");
    }

    public IceCreamTypeNotSupportedException(String message) {
        super(message);
    }

    public IceCreamTypeNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }
}