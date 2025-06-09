package com.iceCreamShop.DesignPatterns.state;

import com.iceCreamShop.DesignPatterns.model.Order;

public class OrderReadyState implements OrderState {
    @Override public void advance(Order order) { order.setState(new OrderDeliveredState()); }
    @Override public void cancel(Order order) { System.out.println("INVALID ACTION: A ready order cannot be cancelled."); }
    @Override public String getDescription() { return "Ready for Pickup"; }
}