package com.iceCreamShop.DesignPatterns.facade;

import com.iceCreamShop.DesignPatterns.exception.OrderStateException;
import com.iceCreamShop.DesignPatterns.model.DiscountType;
import com.iceCreamShop.DesignPatterns.model.Order;
import com.iceCreamShop.DesignPatterns.observer.CustomerObserver;
import com.iceCreamShop.DesignPatterns.repository.OrderRepository;
import com.iceCreamShop.DesignPatterns.singleton.OrderQueue;
import com.iceCreamShop.DesignPatterns.strategy.FrequentCustomerDiscount;
import com.iceCreamShop.DesignPatterns.strategy.NoDiscount;
import com.iceCreamShop.DesignPatterns.strategy.SeasonalDiscount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IceCreamShopFacade {
    private final OrderRepository orderRepository;
    private final OrderQueue orderQueue;

    @Autowired
    public IceCreamShopFacade(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        this.orderQueue = OrderQueue.getInstance();
    }

    public void registerNewOrder(Order order, DiscountType discountType) throws OrderStateException {
        switch (discountType) {
            case FREQUENT -> order.setDiscountStrategy(new FrequentCustomerDiscount());
            case SEASONAL -> order.setDiscountStrategy(new SeasonalDiscount());
            case NONE -> order.setDiscountStrategy(new NoDiscount());
        }

        order.setTotal();
        order.addObserver(new CustomerObserver());
        order = orderRepository.save(order);
        order.initTransientFields();
        orderQueue.addOrder(order);

        if (orderQueue.size() == 1) {
            order.advanceState();
            orderRepository.save(order);
        }
    }

    public void advanceOrderStatus() throws OrderStateException {
        Order nextOrder = orderQueue.peek();

        if (nextOrder != null) {
            nextOrder.advanceState();
            orderRepository.save(nextOrder);
        }

        else {
            throw new RuntimeException();
        }
    }

    public void processNextInQueue() {
        Order order = orderQueue.processNextOrder();
        if (order != null) {
            orderRepository.save(order);
        }
    }
}