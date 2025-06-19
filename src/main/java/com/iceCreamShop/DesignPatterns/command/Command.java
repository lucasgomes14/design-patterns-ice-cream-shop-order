package com.iceCreamShop.DesignPatterns.command;

import com.iceCreamShop.DesignPatterns.exception.OrderStateException;

public interface Command {
    void execute() throws OrderStateException;
}