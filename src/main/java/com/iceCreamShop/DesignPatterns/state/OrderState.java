package com.iceCreamShop.DesignPatterns.state;

import com.iceCreamShop.DesignPatterns.model.Order;

public interface OrderState {
    void advance(Order order);
    void cancel(Order order);
    String getDescription();
}