package com.iceCreamShop.DesignPatterns.exception;

public class UnsupportedIceCreamTypeException extends Exception {

    public UnsupportedIceCreamTypeException() { super("No order in queue");}

    public UnsupportedIceCreamTypeException(String message) {super(message); }

    public UnsupportedIceCreamTypeException(String message, Throwable cause) {
    }
}
