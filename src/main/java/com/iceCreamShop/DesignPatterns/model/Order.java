package com.iceCreamShop.DesignPatterns.model;

import com.iceCreamShop.DesignPatterns.decorator.SyrupDecorator;
import com.iceCreamShop.DesignPatterns.decorator.WhippedCreamDecorator;
import com.iceCreamShop.DesignPatterns.factory.IceCream;
import com.iceCreamShop.DesignPatterns.observer.OrderObserver;
import com.iceCreamShop.DesignPatterns.state.*;
import com.iceCreamShop.DesignPatterns.strategy.DiscountStrategy;
import com.iceCreamShop.DesignPatterns.strategy.NoDiscount;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tb_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IceCream> iceCreams = new ArrayList<>();

    @Column(nullable = false)
    private boolean addSyrup;

    @Column(nullable = false)
    private boolean addWhippedCream;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.RECEIVED;

    @Column(nullable = false)
    private LocalDateTime orderDate = LocalDateTime.now();

    @Transient
    private OrderState state;

    @Transient
    private DiscountStrategy discountStrategy;

    @Transient
    private List<OrderObserver> observers = new ArrayList<>();

    private double total;

    @PostLoad
    public void initTransientFields() {
        this.discountStrategy = new NoDiscount();
        this.observers = new ArrayList<>();

        switch (status) {
            case RECEIVED -> this.state = new OrderReceivedState();
            case READY -> this.state = new OrderReadyState();
            case IN_PREPARATION -> this.state = new OrderInPreparationState();
            case DELIVERED -> this.state = new OrderDeliveredState();
            case CANCELLED -> this.state = new OrderCancelledState();
        }
    }

    public void setState(OrderState newState) {
        this.state = newState;
        this.status = mapStateToStatus(newState);
        System.out.printf("Order #%d changed state to: %s\n", id, state.getDescription());
        notifyObservers();
    }

    private OrderStatus mapStateToStatus(OrderState state) {
        if (state instanceof OrderReceivedState) return OrderStatus.RECEIVED;
        if (state instanceof OrderReadyState) return OrderStatus.READY;
        if (state instanceof OrderInPreparationState) return OrderStatus.IN_PREPARATION;
        if (state instanceof OrderDeliveredState) return OrderStatus.DELIVERED;
        if (state instanceof OrderCancelledState) return OrderStatus.CANCELLED;
        throw new IllegalStateException("Unknown state");
    }

    public void advanceState() { state.advance(this); }

    public void cancel() { state.cancel(this); }

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (OrderObserver observer : observers) {
            observer.update(this);
        }
    }

    public double getItemsTotalPrice() {
        double total = 0;

        for (IceCream iceCream : iceCreams) {
            IceCream decorated = iceCream;

            if (addSyrup) {
                decorated = new SyrupDecorator(decorated);
            }
            if (addWhippedCream) {
                decorated = new WhippedCreamDecorator(decorated);
            }

            total += decorated.getTotalPrice();
        }

        return total;
    }

    public double getFinalPrice() {
        double total = getItemsTotalPrice();
        return discountStrategy != null ? total - discountStrategy.calculateDiscount(this) : total;
    }

    public void setTotal() {
        this.total = getFinalPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return id != null && id.equals(order.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}