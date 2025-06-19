package com.iceCreamShop.DesignPatterns.dto.response;

import com.iceCreamShop.DesignPatterns.dto.IceCreamComplexDTO;
import com.iceCreamShop.DesignPatterns.model.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDTO(String customer,
                               List<IceCreamComplexDTO> iceCreamDTO,
                               double total,
                               LocalDateTime dateTime,
                               OrderStatus status) {
}
