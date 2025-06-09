package com.iceCreamShop.DesignPatterns.state;

import com.iceCreamShop.DesignPatterns.model.Order;

public class OrderInPreparationState implements OrderState {
    @Override public void advance(Order order) { order.setState(new OrderReadyState()); }
    @Override public void cancel(Order order) { order.setState(new OrderCancelledState()); }
    @Override public String getDescription() { return "In Preparation"; }
}