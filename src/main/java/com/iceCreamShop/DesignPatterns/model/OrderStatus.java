package com.iceCreamShop.DesignPatterns.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatus {
    RECEIVED("Recebido"),
    IN_PROGRESS("Em preparo"),
    READY("Pronto para entrega"),
    DELIVERED("Entregue"),
    CANCELLED("Cancelado");

    private final String description;
}
