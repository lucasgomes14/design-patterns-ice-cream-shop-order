package com.iceCreamShop.DesignPatterns.state;

import com.iceCreamShop.DesignPatterns.exception.OrderStateException;
import com.iceCreamShop.DesignPatterns.model.Order;

public class OrderReceivedState implements OrderState {
    @Override public void advance(Order order) throws OrderStateException { order.setState(new OrderInPreparationState()); }
    @Override public void cancel(Order order) throws OrderStateException { order.setState(new OrderCancelledState()); }
    @Override public String getDescription() { return "Received"; }
}