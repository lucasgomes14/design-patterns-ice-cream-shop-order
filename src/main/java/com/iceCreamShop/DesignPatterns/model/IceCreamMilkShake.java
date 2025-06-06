package com.iceCreamShop.DesignPatterns.model;

import java.math.BigDecimal;

public class IceCreamMilkShake implements IceCream {
    public static final String TYPE = "MILKSHAKE";
    private Flavor flavor;

    @Override
    public String getDescription() {
        return "Sorvete de milkshake sabor " + flavor;
    }

    @Override
    public BigDecimal getPrice() {
        return new BigDecimal("15.00");
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
