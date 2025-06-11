package com.iceCreamShop.DesignPatterns.decorator;

import com.iceCreamShop.DesignPatterns.factory.IceCream;
import com.iceCreamShop.DesignPatterns.factory.TypeIceCream;

public class SyrupDecorator extends IceCreamDecorator {
    public SyrupDecorator(IceCream iceCream) { super(iceCream); }

    @Override
    public String getDescription() { return super.getDescription() + ", with chocolate syrup"; }

    @Override
    public double getUnitPrice() { return super.getUnitPrice() + 1.50; }

    @Override
    public int getQuantity() {
        return decoratedIceCream.getQuantity();
    }

    @Override
    public double getTotalPrice() {
        return getUnitPrice() * getQuantity();
    }

    @Override
    public TypeIceCream getType() {
        return type;
    }
}