package com.iceCreamShop.DesignPatterns.service;

import com.iceCreamShop.DesignPatterns.dto.request.ComplexOrderRequestDTO;
import com.iceCreamShop.DesignPatterns.dto.request.SimpleOrderRequestDTO;
import com.iceCreamShop.DesignPatterns.dto.response.CustomerResponseDTO;
import com.iceCreamShop.DesignPatterns.dto.response.OrderResponseDTO;
import com.iceCreamShop.DesignPatterns.model.Order;

import java.util.List;

public interface OrderService {
    void createSimpleOrder(SimpleOrderRequestDTO request);
    void createComplexOrder(ComplexOrderRequestDTO request);
    void advanceOrderStatus();
    void cancelOrder(Long orderId);
    List<OrderResponseDTO> getAllOrders();
    Order getOrderById(Long orderId);
    List<OrderResponseDTO> getOrdersInQueue();

    List<CustomerResponseDTO> getConsumers();
}
