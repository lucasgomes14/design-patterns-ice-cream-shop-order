package com.iceCreamShop.DesignPatterns;

import com.iceCreamShop.DesignPatterns.command.CancelOrderCommand;
import com.iceCreamShop.DesignPatterns.command.Command;
import com.iceCreamShop.DesignPatterns.decorator.SyrupDecorator;
import com.iceCreamShop.DesignPatterns.decorator.WhippedCreamDecorator;
import com.iceCreamShop.DesignPatterns.facade.IceCreamShopFacade;
import com.iceCreamShop.DesignPatterns.factory.IceCream;
import com.iceCreamShop.DesignPatterns.factory.IceCreamFactory;
import com.iceCreamShop.DesignPatterns.model.Customer;
import com.iceCreamShop.DesignPatterns.model.Order;
import com.iceCreamShop.DesignPatterns.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class IceCreamShopRunner implements CommandLineRunner {

    @Autowired private IceCreamFactory iceCreamFactory;
    @Autowired private IceCreamShopFacade facade;
    @Autowired private OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("🍦 Welcome to the Design Patterns Ice Cream Shop! (Running with Spring Boot) 🍦\n");

        // SCENARIO 1: Simple Order
        System.out.println("\n--- START SCENARIO 1: Simple Order ---");
        Customer ana = new Customer("Ana");
        IceCream anasIceCream = iceCreamFactory.createIceCream("scoop", "Strawberry");
        Order order1 = new Order(ana, anasIceCream);
        facade.registerNewOrder(order1, null);
        System.out.printf("Final price for Order #%d: $%.2f\n", order1.getId(), order1.getFinalPrice());
        System.out.println("--- END SCENARIO 1 ---\n");

        // SCENARIO 2: Complex Order with Decorator and Strategy
        System.out.println("\n--- START SCENARIO 2: Complex Order with Discount ---");
        Customer bruno = new Customer("Bruno");
        IceCream brunosIceCream = iceCreamFactory.createIceCream("milkshake", "Cookies & Cream");
        brunosIceCream = new SyrupDecorator(brunosIceCream);
        brunosIceCream = new WhippedCreamDecorator(brunosIceCream);
        System.out.println("Item description: " + brunosIceCream.getDescription());
        System.out.printf("Item price: $%.2f\n", brunosIceCream.getPrice());

        Order order2 = new Order(bruno, brunosIceCream);
        facade.registerNewOrder(order2, "frequent");
        System.out.printf("Final price for Order #%d (with discount): $%.2f\n", order2.getId(), order2.getFinalPrice());
        System.out.println("--- END SCENARIO 2 ---\n");

        // SCENARIO 3: Processing the Queue and State changes
        System.out.println("\n--- START SCENARIO 3: Processing and Tracking ---");
        facade.processNextInQueue(); // Order 1 is processed
        facade.advanceOrderStatus(1); // In Preparation -> Ready
        facade.advanceOrderStatus(1); // Ready -> Delivered

        facade.processNextInQueue(); // Order 2 is processed
        facade.advanceOrderStatus(2); // In Preparation -> Ready
        System.out.println("--- END SCENARIO 3 ---\n");

        // SCENARIO 4: Cancelling an order with Command
        System.out.println("\n--- START SCENARIO 4: Cancelling an Order ---");
        Customer carlos = new Customer("Carlos");
        IceCream carlosIceCream = iceCreamFactory.createIceCream("popsicle", "Lemon");
        Order order3 = new Order(carlos, carlosIceCream);
        facade.registerNewOrder(order3, null);

        Command cancelCommand = new CancelOrderCommand(order3);
        cancelCommand.execute();

        orderRepository.save(order3); // Save the cancelled state
        System.out.println("Final status for Order #3: " + order3.getState().getDescription());
        System.out.println("--- END SCENARIO 4 ---");
    }
}