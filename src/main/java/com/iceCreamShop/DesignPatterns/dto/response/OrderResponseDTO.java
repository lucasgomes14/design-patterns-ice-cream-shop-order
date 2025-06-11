package com.iceCreamShop.DesignPatterns.dto.response;

import com.iceCreamShop.DesignPatterns.dto.IceCreamDTO;
import com.iceCreamShop.DesignPatterns.model.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDTO(String customer,
                               List<IceCreamDTO> iceCreamDTO,
                               double total,
                               LocalDateTime dateTime,
                               OrderStatus status) {
}
