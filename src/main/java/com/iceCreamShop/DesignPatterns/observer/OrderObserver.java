package com.iceCreamShop.DesignPatterns.observer;

import com.iceCreamShop.DesignPatterns.model.Order;

public interface OrderObserver {
    void update(Order order);
}