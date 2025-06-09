package com.iceCreamShop.DesignPatterns.strategy;

import com.iceCreamShop.DesignPatterns.model.Order;

public class SeasonalDiscount implements DiscountStrategy {
    @Override
    public double calculateDiscount(Order order) {
        System.out.println("Applying Seasonal Discount (15%) - Summer Fest!");
        return order.getItemsTotalPrice() * 0.15;
    }
}