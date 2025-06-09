package com.iceCreamShop.DesignPatterns.decorator;

import com.iceCreamShop.DesignPatterns.factory.IceCream;

public class WhippedCreamDecorator extends IceCreamDecorator {
    public WhippedCreamDecorator(IceCream iceCream) { super(iceCream); }

    @Override
    public String getDescription() { return super.getDescription() + ", with whipped cream"; }

    @Override
    public double getPrice() { return super.getPrice() + 2.00; }
}