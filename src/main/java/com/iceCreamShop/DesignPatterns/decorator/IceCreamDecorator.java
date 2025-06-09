package com.iceCreamShop.DesignPatterns.decorator;

import com.iceCreamShop.DesignPatterns.factory.IceCream;

public abstract class IceCreamDecorator implements IceCream {
    protected IceCream decoratedIceCream;

    public IceCreamDecorator(IceCream iceCream) {
        this.decoratedIceCream = iceCream;
    }

    @Override
    public String getDescription() { return decoratedIceCream.getDescription(); }

    @Override
    public double getPrice() { return decoratedIceCream.getPrice(); }
}