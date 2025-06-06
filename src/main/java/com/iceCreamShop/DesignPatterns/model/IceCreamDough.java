package com.iceCreamShop.DesignPatterns.model;

import java.math.BigDecimal;

public class IceCreamDough implements IceCream {
    public static final String TYPE = "DOUGH";
    private Flavor flavor;

    @Override
    public String getDescription() {
        return "Sorvete de massa sabor " + flavor;
    }

    @Override
    public BigDecimal getPrice() {
        return new BigDecimal("8.00");
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String getFlavor() {
        return flavor.getFlavor();
    }

    @Override
    public void setFlavor(Flavor flavor) {
        this.flavor = flavor;
    }
}
