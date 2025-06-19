package com.iceCreamShop.DesignPatterns.state;

import com.iceCreamShop.DesignPatterns.exception.OrderStateException;
import com.iceCreamShop.DesignPatterns.model.Order;

public class OrderInPreparationState implements OrderState {
    @Override public void advance(Order order) throws OrderStateException { order.setState(new OrderReadyState()); }
    @Override public void cancel(Order order) throws OrderStateException { order.setState(new OrderCancelledState()); }
    @Override public String getDescription() { return "In Preparation"; }
}