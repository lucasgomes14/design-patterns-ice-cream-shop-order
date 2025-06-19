package com.iceCreamShop.DesignPatterns.controller;

import com.iceCreamShop.DesignPatterns.dto.request.ComplexOrderRequestDTO;
import com.iceCreamShop.DesignPatterns.dto.request.SimpleOrderRequestDTO;
import com.iceCreamShop.DesignPatterns.dto.response.CustomerResponseDTO;
import com.iceCreamShop.DesignPatterns.dto.response.OrderResponseDTO;
import com.iceCreamShop.DesignPatterns.exception.IceCreamTypeNotSupportedException;
import com.iceCreamShop.DesignPatterns.exception.OrderStateException;
import com.iceCreamShop.DesignPatterns.model.Order;
import com.iceCreamShop.DesignPatterns.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;

    @Override
    public ResponseEntity<Void> createSimpleOrder(SimpleOrderRequestDTO request) throws OrderStateException, IceCreamTypeNotSupportedException {
        orderService.createSimpleOrder(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> createComplexOrder(ComplexOrderRequestDTO request) throws OrderStateException, IceCreamTypeNotSupportedException {
        orderService.createComplexOrder(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> advanceOrderStatus() throws OrderStateException {
        orderService.advanceOrderStatus();

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<Void> cancelOrder(Long orderId) throws OrderStateException {
        orderService.cancelOrder(orderId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        List<OrderResponseDTO> orders = orderService.getAllOrders();

        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @Override
    public ResponseEntity<Order> getOrderById(Long orderId) {
        return null;
    }

    @Override
    public ResponseEntity<List<OrderResponseDTO>> getOrdersInQueue() {
        List<OrderResponseDTO> orders = orderService.getOrdersInQueue();

        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @Override
    public ResponseEntity<List<CustomerResponseDTO>> getConsumers() {
        List<CustomerResponseDTO> consumers = orderService.getConsumers();

        return ResponseEntity.status(HttpStatus.OK).body(consumers);
    }
}
