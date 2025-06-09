package com.iceCreamShop.DesignPatterns.command;

import com.iceCreamShop.DesignPatterns.model.Order;

public class CancelOrderCommand implements Command {
    private Order order;

    public CancelOrderCommand(Order order) { this.order = order; }

    @Override
    public void execute() {
        System.out.printf("Executing command to cancel order #%d\n", order.getId());
        order.cancel();
    }
}