package com.iceCreamShop.DesignPatterns.model;

import java.math.BigDecimal;

public class IceCreamPopsicle implements IceCream {
    public static final String TYPE = "POPSICLE";
    private Flavor flavor;

    @Override
    public String getDescription() {
        return "Sorvete de picolé sabor " + flavor;
    }

    @Override
    public BigDecimal getPrice() {
        return new BigDecimal("4.00");
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
