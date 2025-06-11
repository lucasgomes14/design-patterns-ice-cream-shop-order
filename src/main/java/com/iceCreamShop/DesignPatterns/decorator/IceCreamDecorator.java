package com.iceCreamShop.DesignPatterns.decorator;

import com.iceCreamShop.DesignPatterns.factory.IceCream;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class IceCreamDecorator extends IceCream {

    @Transient
    protected IceCream decoratedIceCream;

    @Override
    public String getFlavor() {
        return decoratedIceCream.getFlavor();
    }

    @Override
    public String getDescription() {
        return decoratedIceCream.getDescription();
    }

    @Override
    public double getUnitPrice() {
        return decoratedIceCream.getUnitPrice();
    }
}