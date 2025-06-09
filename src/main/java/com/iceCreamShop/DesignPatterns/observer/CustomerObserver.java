package com.iceCreamShop.DesignPatterns.observer;

import com.iceCreamShop.DesignPatterns.model.Order;

public class CustomerObserver implements OrderObserver {
    @Override
    public void update(Order order) {
        System.out.println("== NOTIFICATION FOR CUSTOMER ==");
        System.out.printf("Hello, %s! The status of your order #%d has been updated to: %s\n",
                order.getCustomer().getName(),
                order.getId(),
                order.getState().getDescription());
        System.out.println("=============================");
    }
}