package onlinestore.service;

import onlinestore.model.Order;
import onlinestore.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class OrderService {
    private final List<Order> orders;

    public OrderService(List<Order> orders) {
        this.orders = orders;
    }

    public Order createNewOrder(long orderId) {
        Order order = new Order();
        order.setOrderId(orderId);
        return order;
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
