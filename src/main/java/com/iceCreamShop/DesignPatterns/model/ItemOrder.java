package com.iceCreamShop.DesignPatterns.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "tb_order_items")
public class ItemOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(nullable = false)
    private String iceCreamType;

    @Column(nullable = false)
    private Flavor flavor;

    @ElementCollection
    @CollectionTable(name = "item_additions", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "addition")
    private List<String> additions;

    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private Integer quantity;

    public BigDecimal getSubTotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public static ItemOrderBuilder builder() {
        return new ItemOrderBuilder();
    }

    public static class ItemOrderBuilder {
        private String iceCreamType;
        private Flavor flavor;
        private List<String> additions;
        private BigDecimal unitPrice;
        private Integer quantity;

        public ItemOrderBuilder iceCreamType(String iceCreamType) {
            this.iceCreamType = iceCreamType;
            return this;
        }

        public ItemOrder build() {
            ItemOrder item = new ItemOrder();
            item.setIceCreamType(iceCreamType);
            item.setFlavor(flavor);
            item.setAdditions(additions);
            item.setUnitPrice(unitPrice);
            item.setQuantity(quantity);
            return item;
        }
    }
}