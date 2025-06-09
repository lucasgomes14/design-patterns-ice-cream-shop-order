package com.iceCreamShop.DesignPatterns.strategy;

import com.iceCreamShop.DesignPatterns.model.Order;

public class FrequentCustomerDiscount implements DiscountStrategy {
    @Override
    public double calculateDiscount(Order order) {
        System.out.println("Applying Frequent Customer Discount (10%)");
        return order.getItemsTotalPrice() * 0.10;
    }
}