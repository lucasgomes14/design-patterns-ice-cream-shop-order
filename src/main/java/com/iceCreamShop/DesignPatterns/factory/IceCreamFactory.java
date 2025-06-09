package com.iceCreamShop.DesignPatterns.factory;

import org.springframework.stereotype.Component;

@Component
public class IceCreamFactory {
    public IceCream createIceCream(String type, String flavor) {
        if ("scoop".equalsIgnoreCase(type)) {
            return new ScoopIceCream(flavor);
        } else if ("popsicle".equalsIgnoreCase(type)) {
            return new Popsicle(flavor);
        } else if ("milkshake".equalsIgnoreCase(type)) {
            return new Milkshake(flavor);
        }
        throw new IllegalArgumentException("Unknown ice cream type: " + type);
    }
}