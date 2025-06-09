package com.iceCreamShop.DesignPatterns.strategy;

import com.iceCreamShop.DesignPatterns.model.Order;

public interface DiscountStrategy {
    double calculateDiscount(Order order);
}