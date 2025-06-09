package com.iceCreamShop.DesignPatterns.state;

import com.iceCreamShop.DesignPatterns.model.Order;

public class OrderDeliveredState implements OrderState {
    @Override public void advance(Order order) { System.out.println("INVALID ACTION: The order has already been delivered."); }
    @Override public void cancel(Order order) { System.out.println("INVALID ACTION: A delivered order cannot be cancelled."); }
    @Override public String getDescription() { return "Delivered"; }
}