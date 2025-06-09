package com.iceCreamShop.DesignPatterns.strategy;

import com.iceCreamShop.DesignPatterns.model.Order;

public class NoDiscount implements DiscountStrategy {
    @Override
    public double calculateDiscount(Order order) {
        System.out.println("No discount applicable.");
        return 0.0;
    }
}