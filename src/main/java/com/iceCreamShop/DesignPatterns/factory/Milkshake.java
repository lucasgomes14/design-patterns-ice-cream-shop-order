package com.iceCreamShop.DesignPatterns.factory;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@DiscriminatorValue("milkshake")
public class Milkshake extends IceCream {
    public Milkshake(String flavor, double unitPrice, int quantity, TypeIceCream type) {
        super(flavor, unitPrice, quantity, type);
    }

    @Override
    public String getDescription() {
        return flavor + " Milkshake";
    }

    @Override
    public double getUnitPrice() {
        return unitPrice;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public double getTotalPrice() {
        return unitPrice * quantity;
    }

    @Override
    public TypeIceCream getType() {
        return type;
    }
}