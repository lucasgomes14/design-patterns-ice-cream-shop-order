package com.iceCreamShop.DesignPatterns.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Flavor {
    CHOCOLATE("chocolate"),
    STRAWBERRY("strawberry"),
    VANILLA("vanilla");

    private final String flavor;
}
