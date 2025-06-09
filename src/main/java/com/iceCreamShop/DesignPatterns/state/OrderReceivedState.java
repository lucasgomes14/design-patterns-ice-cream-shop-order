package com.iceCreamShop.DesignPatterns.state;

import com.iceCreamShop.DesignPatterns.model.Order;

public class OrderReceivedState implements OrderState {
    @Override public void advance(Order order) { order.setState(new OrderInPreparationState()); }
    @Override public void cancel(Order order) { order.setState(new OrderCancelledState()); }
    @Override public String getDescription() { return "Received"; }
}