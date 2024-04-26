package onlinestore.service;

import onlinestore.model.order.Order;
import onlinestore.model.product.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderServiceTest {

    private OrderService orderService;

    @Test
    void createNewOrder() {
        long orderId = 1L;
        orderService = new OrderService(new ArrayList<>());

        Order newOrder = orderService.createNewOrder(orderId);

        assertEquals(orderId, newOrder.getOrderId());
    }

    @Test
    void addProduct() {
        orderService = new OrderService(new ArrayList<>());
        Order order = Order.builder().build();
        Product product = Product.builder().build();
        int amount = 3;

        orderService.addProduct(order, product, amount);

        assertEquals(product.getPrice() * amount, order.getTotal());
        assertEquals(1, order.getProducts().size());
    }

    @Test
    void getCompleteOrders() {
        orderService = new OrderService(List.of(
                Order.builder().isComplete(false).build(),
                Order.builder().isComplete(true).build()
        ));

        List<Order> completeOrders = orderService.getCompleteOrders();

        assertEquals(1, completeOrders.size());
    }

    @Test
    void generateNewOrderId() {
        orderService = new OrderService(List.of(
                Order.builder().orderId(1).build(),
                Order.builder().orderId(2).build()
        ));

        long orderId = orderService.generateNewOrderId();

        assertEquals(3, orderId);
    }

    @Test
    void completeOrder() {
        orderService = new OrderService(new ArrayList<>());

        orderService.completeOrder(Order.builder().build());

        assertEquals(1, orderService.getCompleteOrders().size());
        assertTrue(orderService.getCompleteOrders().getFirst().isComplete());
    }
}