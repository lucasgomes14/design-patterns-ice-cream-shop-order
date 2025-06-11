package com.iceCreamShop.DesignPatterns.factory;

import com.iceCreamShop.DesignPatterns.model.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@DiscriminatorColumn(name = "ice_cream_type")
public abstract class IceCream {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected String flavor;

    protected double unitPrice;

    protected double totalPrice;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    protected TypeIceCream type;

    @Min(value = 1, message = "A quantidade deve ser no mínimo 1")
    protected int quantity;

    public IceCream(String flavor, double unitPrice, int quantity, TypeIceCream type) {
        this.flavor = flavor;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        totalPrice = unitPrice * quantity;
        this.type = type;
    }

    public abstract String getDescription();
    public abstract double getUnitPrice();
    public abstract int getQuantity();
    public abstract double getTotalPrice();
    public abstract TypeIceCream getType();
}
