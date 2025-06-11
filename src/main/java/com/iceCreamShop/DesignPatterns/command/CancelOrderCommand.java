package com.iceCreamShop.DesignPatterns.command;

import com.iceCreamShop.DesignPatterns.model.Order;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CancelOrderCommand implements Command {
    private Order order;

    @Override
    public void execute() {
        System.out.printf("Executing command to cancel order #%d\n", order.getId());
        order.cancel();
    }
}