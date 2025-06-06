package com.iceCreamShop.DesignPatterns.repository;

import com.iceCreamShop.DesignPatterns.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
