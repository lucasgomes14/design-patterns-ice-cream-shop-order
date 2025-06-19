package com.iceCreamShop.DesignPatterns.service;

import com.iceCreamShop.DesignPatterns.dto.request.ComplexOrderRequestDTO;
import com.iceCreamShop.DesignPatterns.dto.request.SimpleOrderRequestDTO;
import com.iceCreamShop.DesignPatterns.dto.response.CustomerResponseDTO;
import com.iceCreamShop.DesignPatterns.dto.response.OrderResponseDTO;
import com.iceCreamShop.DesignPatterns.exception.IceCreamTypeNotSupportedException;
import com.iceCreamShop.DesignPatterns.exception.OrderStateException;
import com.iceCreamShop.DesignPatterns.model.Order;

import java.util.List;

public interface OrderService {
    void createSimpleOrder(SimpleOrderRequestDTO request) throws IceCreamTypeNotSupportedException, OrderStateException;
    void createComplexOrder(ComplexOrderRequestDTO request) throws IceCreamTypeNotSupportedException, OrderStateException;
    void advanceOrderStatus() throws OrderStateException;
    void cancelOrder(Long orderId) throws OrderStateException;
    List<OrderResponseDTO> getAllOrders();
    OrderResponseDTO getOrderById(Long orderId);
    List<OrderResponseDTO> getOrdersInQueue();

    List<CustomerResponseDTO> getConsumers();
}
