package onlinestore.service;

import onlinestore.model.order.Client;
import onlinestore.model.order.Order;
import onlinestore.model.order.ProductInOrder;
import onlinestore.model.product.Measure;
import onlinestore.model.product.Product;
import onlinestore.service.utils.StringUtils;
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


        if(order.getDevileryOption() == null)
            throw new RuntimeException("You need to specify delivery option");

        validateClient(order.getClient());

        for (ProductInOrder pio : order.getProducts()) {
            Measure productMeasure = pio.getProduct().getMeasure();
            if (productMeasure != Measure.KG && pio.getQuantity() != (float) Math.round(pio.getQuantity()))
                throw new RuntimeException("You need to specify delivery option");
        }

        // products -> number not more than in stock
        // delivery options details validation
        //TODO: HW -> finish validations + think how to clean up validation

        orderRepository.saveEntity(order);
    }

    private void validateClient(Client client) {
        if (client == null)
            throw new RuntimeException("You need to specify client");

        if (StringUtils.isAnyBlank(client.getClientFirstName(),
                client.getClientLastName(), client.getPhoneNumber()))
            throw new RuntimeException("Client should have first name, last name and phone number");

        String phoneRegex = "^(0|\\+380)\\d{9}$";
        if (!client.getPhoneNumber().matches(phoneRegex))
            throw new RuntimeException("Phone number should be started from 0 or +380 and contains 9 digits after");
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
