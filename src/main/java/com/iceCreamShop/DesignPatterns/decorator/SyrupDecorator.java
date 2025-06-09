package com.iceCreamShop.DesignPatterns.decorator;

import com.iceCreamShop.DesignPatterns.factory.IceCream;

public class SyrupDecorator extends IceCreamDecorator {
    public SyrupDecorator(IceCream iceCream) { super(iceCream); }

    @Override
    public String getDescription() { return super.getDescription() + ", with chocolate syrup"; }

    @Override
    public double getPrice() { return super.getPrice() + 1.50; }
}