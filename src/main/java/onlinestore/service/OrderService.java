package onlinestore.service;

import onlinestore.model.order.Order;
import onlinestore.model.product.Product;
import repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

public class OrderService {
    private OrderRepository orderRepository;


    public OrderService() {
        this.orderRepository = new OrderRepository();
    }

    public void createOrder(Order order){
        if(order.getProducts().isEmpty())
            throw new RuntimeException("You need to have at least one product in the order");
        //TODO: HW -> what should be validated? prepare a list

        orderRepository.saveEntity(order);
    }

    public Order createNewOrder(long orderId) {
        return Order.builder()
                .orderId(orderId)
                .build();
    }

    public void addProduct(Order order, Product product, int amount) {
        order.getProducts().add(product);
        product.setQuantity(product.getQuantity() - amount);
        order.setTotal(order.getTotal() + (product.getPrice() * amount));
    }

    public List<Order> getCompleteOrders() {
        return orders.stream()
                .filter(Order::isComplete)
                .collect(Collectors.toList());
    }

    public long generateNewOrderId() {
        return orders.stream()
                .map(Order::getOrderId)
                .max(Long::compareTo)
                .orElse(0L) + 1;
    }

    public void completeOrder(Order order) {
        order.setComplete(true);
        this.orders.add(order);
    }
}
