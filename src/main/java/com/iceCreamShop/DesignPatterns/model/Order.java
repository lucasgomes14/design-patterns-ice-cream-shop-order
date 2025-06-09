package com.iceCreamShop.DesignPatterns.model;

import com.iceCreamShop.DesignPatterns.factory.IceCream;
import com.iceCreamShop.DesignPatterns.observer.OrderObserver;
import com.iceCreamShop.DesignPatterns.state.OrderReceivedState;
import com.iceCreamShop.DesignPatterns.state.OrderState;
import com.iceCreamShop.DesignPatterns.strategy.DiscountStrategy;
import com.iceCreamShop.DesignPatterns.strategy.NoDiscount;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Order {
    private static final AtomicInteger idCounter = new AtomicInteger(0);
    private int id;
    private Customer customer;
    private IceCream iceCream;
    private OrderState state;
    private DiscountStrategy discountStrategy;
    private List<OrderObserver> observers = new ArrayList<>();

    public Order(Customer customer, IceCream iceCream) {
        this.id = idCounter.incrementAndGet();
        this.customer = customer;
        this.iceCream = iceCream;
        this.state = new OrderReceivedState();
        this.discountStrategy = new NoDiscount(); // Default strategy
    }

    public void setState(OrderState newState) {
        this.state = newState;
        System.out.printf("Order #%d changed state to: %s\n", id, state.getDescription());
        notifyObservers();
    }

    public void advanceState() { state.advance(this); }
    public void cancel() { state.cancel(this); }

    public void addObserver(OrderObserver observer) { observers.add(observer); }
    public void notifyObservers() {
        for (OrderObserver observer : observers) {
            observer.update(this);
        }
    }

    public double getItemsTotalPrice() { return iceCream.getPrice(); }

    public double getFinalPrice() {
        double total = getItemsTotalPrice();
        double discount = discountStrategy.calculateDiscount(this);
        return total - discount;
    }

    // Getters and Setters
    public int getId() { return id; }
    public Customer getCustomer() { return customer; }
    public IceCream getIceCream() { return iceCream; }
    public OrderState getState() { return state; }
    public void setDiscountStrategy(DiscountStrategy discountStrategy) { this.discountStrategy = discountStrategy; }
}