package com.iceCreamShop.DesignPatterns.factory;

public class ScoopIceCream implements IceCream {
    private String flavor;
    public ScoopIceCream(String flavor) { this.flavor = flavor; }

    @Override
    public String getDescription() { return "Scoop ice cream, " + flavor + " flavor"; }

    @Override
    public double getPrice() { return 8.00; }
}