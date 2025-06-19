package com.iceCreamShop.DesignPatterns.factory;

import com.iceCreamShop.DesignPatterns.exception.IceCreamTypeNotSupportedException;
import org.springframework.stereotype.Component;

@Component
public class IceCreamFactory {
    public IceCream createIceCream(TypeIceCream type, String flavor, int quantity) throws IceCreamTypeNotSupportedException {
        return switch (type) {
            case SCOOP_ICE_CREAM -> new ScoopIceCream(flavor, 8.00, quantity, type);
            case POPSICLE -> new Popsicle(flavor, 5.00, quantity, type);
            case MILKSHAKE -> new Milkshake(flavor, 12.50, quantity, type);
            default -> throw new IceCreamTypeNotSupportedException("Unsupported ice cream type: " + type);
        };
    }
}