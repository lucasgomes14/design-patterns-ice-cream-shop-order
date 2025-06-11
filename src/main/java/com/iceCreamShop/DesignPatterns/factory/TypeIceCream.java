package com.iceCreamShop.DesignPatterns.factory;

import lombok.Getter;

@Getter
public enum TypeIceCream {
    MILKSHAKE("milkshake"),
    POPSICLE("popsicle"),
    SCOOP_ICE_CREAM("scoop ice cream");

    private final String type;

    TypeIceCream(String type) {
        this.type = type.toUpperCase();
    }
}
