package com.iceCreamShop.DesignPatterns.state;

import com.iceCreamShop.DesignPatterns.model.Order;

public class OrderCancelledState implements OrderState {
    @Override public void advance(Order order) { System.out.println("INVALID ACTION: A cancelled order cannot be advanced."); }
    @Override public void cancel(Order order) { System.out.println("INVALID ACTION: The order is already cancelled."); }
    @Override public String getDescription() { return "Cancelled"; }
}