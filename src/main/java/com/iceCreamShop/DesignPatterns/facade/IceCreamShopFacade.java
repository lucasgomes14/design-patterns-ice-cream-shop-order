package com.iceCreamShop.DesignPatterns.facade;

import com.iceCreamShop.DesignPatterns.model.Order;
import com.iceCreamShop.DesignPatterns.observer.CustomerObserver;
import com.iceCreamShop.DesignPatterns.repository.OrderRepository;
import com.iceCreamShop.DesignPatterns.singleton.OrderQueue;
import com.iceCreamShop.DesignPatterns.strategy.FrequentCustomerDiscount;
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

    public void registerNewOrder(Order order, String discountType) {
        if ("frequent".equalsIgnoreCase(discountType)) {
            order.setDiscountStrategy(new FrequentCustomerDiscount());
        } else if ("seasonal".equalsIgnoreCase(discountType)) {
            order.setDiscountStrategy(new SeasonalDiscount());
        }
        order.addObserver(new CustomerObserver());
        orderRepository.save(order);
        orderQueue.addOrder(order);
    }

    public void advanceOrderStatus(int orderId) {
        orderRepository.findById(orderId).ifPresent(Order::advanceState);
    }

    public void processNextInQueue() {
        Order order = orderQueue.processNextOrder();
        if (order != null) {
            orderRepository.save(order);
        }
    }
}