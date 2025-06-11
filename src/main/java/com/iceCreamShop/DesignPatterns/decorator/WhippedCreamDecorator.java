package com.iceCreamShop.DesignPatterns.decorator;

import com.iceCreamShop.DesignPatterns.factory.IceCream;
import com.iceCreamShop.DesignPatterns.factory.TypeIceCream;

public class WhippedCreamDecorator extends IceCreamDecorator {
    public WhippedCreamDecorator(IceCream iceCream) { super(iceCream); }

    @Override
    public String getDescription() { return super.getDescription() + ", with whipped cream"; }

    @Override
    public double getUnitPrice() { return super.getUnitPrice() + 2.00; }

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