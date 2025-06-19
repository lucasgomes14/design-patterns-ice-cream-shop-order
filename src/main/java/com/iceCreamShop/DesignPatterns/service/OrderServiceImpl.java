package com.iceCreamShop.DesignPatterns.service;

import com.iceCreamShop.DesignPatterns.command.CancelOrderCommand;
import com.iceCreamShop.DesignPatterns.command.Command;
import com.iceCreamShop.DesignPatterns.dto.IceCreamDTO;
import com.iceCreamShop.DesignPatterns.dto.request.ComplexOrderRequestDTO;
import com.iceCreamShop.DesignPatterns.dto.request.SimpleOrderRequestDTO;
import com.iceCreamShop.DesignPatterns.dto.response.CustomerResponseDTO;
import com.iceCreamShop.DesignPatterns.dto.response.OrderResponseDTO;
import com.iceCreamShop.DesignPatterns.exception.IceCreamTypeNotSupportedException;
import com.iceCreamShop.DesignPatterns.exception.OrderStateException;
import com.iceCreamShop.DesignPatterns.facade.IceCreamShopFacade;
import com.iceCreamShop.DesignPatterns.factory.IceCream;
import com.iceCreamShop.DesignPatterns.factory.IceCreamFactory;
import com.iceCreamShop.DesignPatterns.factory.TypeIceCream;
import com.iceCreamShop.DesignPatterns.model.Customer;
import com.iceCreamShop.DesignPatterns.model.Order;
import com.iceCreamShop.DesignPatterns.model.OrderStatus;
import com.iceCreamShop.DesignPatterns.repository.CustomerRepository;
import com.iceCreamShop.DesignPatterns.repository.IceCreamRepository;
import com.iceCreamShop.DesignPatterns.repository.OrderRepository;
import com.iceCreamShop.DesignPatterns.singleton.OrderQueue;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final CustomerRepository customerRepository;
    private final IceCreamRepository iceCreamRepository;
    private final OrderRepository orderRepository;
    private final IceCreamFactory factory;
    private final IceCreamShopFacade facade;
    private final OrderQueue orderQueue;

    public OrderServiceImpl(CustomerRepository customerRepository, IceCreamRepository iceCreamRepository, OrderRepository orderRepository, IceCreamFactory factory, IceCreamShopFacade facade) {
        this.customerRepository = customerRepository;
        this.iceCreamRepository = iceCreamRepository;
        this.orderRepository = orderRepository;
        this.factory = factory;
        this.facade = facade;
        this.orderQueue = OrderQueue.getInstance();
    }

    @Override
    public void createSimpleOrder(SimpleOrderRequestDTO request) throws IceCreamTypeNotSupportedException, OrderStateException {
        Customer customer = saveCustomer(request.customerName());
        List<IceCream> iceCreamList = new ArrayList<>();

        for (IceCreamDTO iceCream : request.iceCreams()) {

            iceCreamList.add(saveIceCream(iceCream.iceCreamType(), iceCream.flavor(), iceCream.quantity()));
        }

        Order order = buildOrder(customer, iceCreamList, false, false);
        facade.registerNewOrder(order, request.discountType());
    }

    @Override
    public void createComplexOrder(ComplexOrderRequestDTO request) throws IceCreamTypeNotSupportedException, OrderStateException {
        Customer customer = saveCustomer(request.customerName());
        List<IceCream> iceCreamList = new ArrayList<>();

        for (IceCreamDTO dto : request.iceCreams()) {
            IceCream saved = saveIceCream(dto.iceCreamType(), dto.flavor(), dto.quantity());
            iceCreamList.add(saved);
        }

        Order order = buildOrder(customer, iceCreamList, request.addSyrup(), request.addWhippedCream());
        facade.registerNewOrder(order, request.discountType());
    }

    @Override
    public void advanceOrderStatus() throws OrderStateException {
        Order order = orderQueue.peek();

        if (order.getStatus() == OrderStatus.CANCELLED) {
            orderQueue.processNextOrder();
            return;
        }

        order.advanceState();
        orderRepository.save(order);

        if (order.getStatus() == OrderStatus.DELIVERED) {
            orderQueue.processNextOrder();
        }
    }

    @Override
    public void cancelOrder(Long orderId) throws OrderStateException {
        Order order = getOrderById(orderId);

        if (order.getStatus() == OrderStatus.DELIVERED || order.getStatus() == OrderStatus.CANCELLED) {
            throw new OrderStateException();
        }

        if (order.equals(orderQueue.peek())) {
            orderQueue.remove(order);
            Order firstQueuedOrder = orderQueue.peek();
            firstQueuedOrder.advanceState();
            orderRepository.save(firstQueuedOrder);
        } else {
            orderQueue.remove(order);
        }

        Command cancelCommand = new CancelOrderCommand(order);
        cancelCommand.execute();
        orderRepository.save(order);
    }

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        return getListOfOrders(orders);
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public List<OrderResponseDTO> getOrdersInQueue() {
        List<Order> orders = orderQueue.getOrders();

        return getListOfOrders(orders);
    }

    @Override
    public List<CustomerResponseDTO> getConsumers() {
        List<Customer> customer = customerRepository.findAll();
        List<CustomerResponseDTO> customerResponseDTOList = new ArrayList<>();

        for (Customer c : customer) {
            customerResponseDTOList.add(new CustomerResponseDTO(c.getName()));
        }
        return customerResponseDTOList;
    }

    private Customer saveCustomer(String name) {
        Customer customer = new Customer();
        customer.setName(name);

        return customerRepository.save(customer);
    }

    private IceCream saveIceCream(TypeIceCream typeIceCream, String flavor, int quantity) throws IceCreamTypeNotSupportedException {
        IceCream iceCream = factory.createIceCream(typeIceCream, flavor, quantity);

        return iceCreamRepository.save(iceCream);
    }

    private Order buildOrder(Customer customer, List<IceCream> iceCreams, boolean addSyrup, boolean addWhippedCream) {
        Order order = new Order();
        order.setCustomer(customer);
        order.setAddSyrup(addSyrup);
        order.setAddWhippedCream(addWhippedCream);

        for (IceCream iceCream : iceCreams) {
            iceCream.setOrder(order);
        }

        order.setIceCreams(iceCreams);

        return order;
    }

    private List<OrderResponseDTO> getListOfOrders(List<Order> orders) {
        List<OrderResponseDTO> orderResponseDTOList = new ArrayList<>();

        for (Order order : orders) {
            List<IceCreamDTO> iceCreamList = new ArrayList<>();

            for (IceCream iceCream : order.getIceCreams()) {
                iceCreamList.add(new IceCreamDTO(iceCream.getType(), iceCream.getFlavor(), iceCream.getOrder().isAddSyrup(), iceCream.getOrder().isAddWhippedCream(), iceCream.getQuantity()));
            }
            orderResponseDTOList.add(new OrderResponseDTO(order.getCustomer().getName(), iceCreamList, order.getTotal(), order.getOrderDate(), order.getStatus()));
        }

        return orderResponseDTOList;
    }
}
