package com.iceCreamShop.DesignPatterns.model;

import java.math.BigDecimal;

public interface IceCream {
    String getDescription();
    BigDecimal getPrice();
    String getType();
    String getFlavor();
    void setFlavor(Flavor flavor);
}
