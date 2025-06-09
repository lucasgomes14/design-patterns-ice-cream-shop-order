package com.iceCreamShop.DesignPatterns.factory;

public class Milkshake implements IceCream {
    private String flavor;
    public Milkshake(String flavor) { this.flavor = flavor; }

    @Override
    public String getDescription() { return flavor + " Milkshake"; }

    @Override
    public double getPrice() { return 12.50; }
}