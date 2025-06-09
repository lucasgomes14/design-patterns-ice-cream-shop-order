package com.iceCreamShop.DesignPatterns.factory;

public class Popsicle implements IceCream {
    private String flavor;
    public Popsicle(String flavor) { this.flavor = flavor; }

    @Override
    public String getDescription() { return "Popsicle, " + flavor + " flavor"; }

    @Override
    public double getPrice() { return 5.00; }
}