package com.iceCreamShop.DesignPatterns.singleton;

import com.iceCreamShop.DesignPatterns.model.Order;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class OrderQueue {
    private static OrderQueue instance;
    private Queue<Order> queue = new LinkedList<>();

    private OrderQueue() {}

    public static synchronized OrderQueue getInstance() {
        if (instance == null) {
            instance = new OrderQueue();
        }
        return instance;
    }

    public void addOrder(Order order) {
        queue.add(order);
        System.out.printf("Order #%d added to the queue. Items in queue: %d\n", order.getId(), queue.size());
    }

    public Order processNextOrder() {
        if (queue.isEmpty()) {
            System.out.println("Order queue is empty.");
            return null;
        }
        Order nextOrder = queue.poll();
        System.out.printf("Processing order #%d from the queue...\n", nextOrder.getId());
        nextOrder.advanceState();
        return nextOrder;
    }

    public List<Order> getOrders() {
        return new ArrayList<>(queue);
    }

    public Order peek() {
        return queue.peek();
    }

    public int size() {
        return queue.size();
    }

    public void remove(Order order) {
        queue.remove(order);
    }
}