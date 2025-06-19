package com.iceCreamShop.DesignPatterns.state;

import com.iceCreamShop.DesignPatterns.exception.OrderStateException;
import com.iceCreamShop.DesignPatterns.model.Order;

public interface OrderState {
    void advance(Order order) throws OrderStateException;
    void cancel(Order order) throws OrderStateException;
    String getDescription();
}