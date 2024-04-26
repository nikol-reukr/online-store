package onlinestore.controller;

import onlinestore.exception.ProductAmountExceeded;
import onlinestore.exception.ProductNotFoundException;
import onlinestore.model.order.Order;
import onlinestore.model.product.Product;
import onlinestore.service.DiscountService;
import onlinestore.service.OrderService;
import onlinestore.service.ProductService;

import java.util.Scanner;

public class OrderController {
    private final Scanner inputScanner;
    private final OrderService orderService;
    private final ProductService productService;
    private final DiscountService discountService;

    public OrderController(Scanner inputScanner, OrderService orderService, ProductService productService, DiscountService discountService) {
        this.inputScanner = inputScanner;
        this.orderService = orderService;
        this.productService = productService;
        this.discountService = discountService;
    }

    public void createOrder() {
        boolean completeOrder = false;
        Order order = orderService.createNewOrder(orderService.generateNewOrderId());

        productService.getAvailableProducts()
                .forEach(product -> System.out.printf("%s. %s: quantity %s, price %s%n", product.getId(), product.getName(), product.getQuantity(), product.getPrice()));

        System.out.println("0. Complete Order");

        do {
            System.out.println("Select Product or Complete Order");
            int choice = inputScanner.nextInt();

            if (choice == 0) {
                completeOrder = true;

                System.out.println("Do you have promo code? Yes - 1, No - 2");

                choice = inputScanner.nextInt();
                inputScanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("Enter your promo code: ");
                        String code = inputScanner.nextLine();
                        Integer discount = discountService.getDiscountByPromocode(code);
                        if (discount > 0) {
                            discountService.applyDiscount(discount, order);
                        } else {
                            System.out.println("This promo code doesn't exist");
                        }
                        break;
                    case 2:
                        break;
                    default:
                        System.out.println("No discount found");
                }
            } else {
                try {
                    Product product = productService.getProduct(choice);

                    System.out.println("Available product amount is " + product.getQuantity());
                    System.out.println("Enter amount of product to buy");
                    int amount = inputScanner.nextInt();

                    if (amount > product.getQuantity()) {
                        throw new ProductAmountExceeded("Oops, looks like we ran out of stock! We are sorry for inconvenience :(");
                    }

                    orderService.addProduct(order, product, amount);
                } catch (ProductNotFoundException | ProductAmountExceeded e) {
                    System.out.println(e.getMessage());
                }
            }
        } while (!completeOrder);

        orderService.completeOrder(order);

        System.out.println("Total to pay: " + order.getTotal());
    }

    public void viewOrderHistory() {
        System.out.println("************Order History***********");
        System.out.println("____________________________________");

        orderService.getCompleteOrders().forEach(order -> {
            System.out.printf("OrderId: %s%n", order.getOrderId());
            order.getProducts().forEach(product -> System.out.printf("%s%n", product.getName()));
            System.out.printf("Total: %s%n", order.getTotal());
        });
    }
}
