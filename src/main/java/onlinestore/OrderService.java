package onlinestore;

import onlinestore.exceptions.ProductNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class OrderService {
    private final List<Order> orders = new ArrayList<>();
    private final Map<String, Integer> promocodes = Map.of(
            "PROMO2024", 50,
            "BIGBOSS007", 99
    );
    private final Scanner scanner = new Scanner(System.in);
    private final ProductCatalogue productCatalogue = new ProductCatalogue();

    public void handleOrder() {
        Order order = new Order();
        float total = 0;
        boolean completeOrder = false;

        productCatalogue.viewProducts();
        System.out.println("0. Complete Order");

        do {
            System.out.println("Select Product or Complete Order");
            int choice = scanner.nextInt();

            if (choice == 0) {
                completeOrder = true;

                System.out.println("Do you have promo code? Yes - 1, No - 2");

                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("Enter your promo code: ");
                        String code = scanner.nextLine();
                        Integer discount = promocodes.get(code);
                        if (discount != null) {
                            total = total - (total * discount / 100);
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
                    Product product = productCatalogue.getProduct(choice);
                    total += product.getPrice();
                    order.addProduct(product);
                    product.setQuantity(product.getQuantity() - 1);
                } catch (ProductNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }
        } while (!completeOrder);

        System.out.println("Total to pay: " + total);
        order.setTotal(total);
        orders.add(order);
        order.setOrderId(orders.size());
    }

    public void viewHistory() {
        System.out.println("************Order History***********");
        System.out.println("____________________________________");
        orders.forEach(order -> {
            System.out.printf("OrderId: %s%n", order.getOrderId());
            order.viewProducts();
            System.out.printf("Total: %s%n", order.getTotal());
        });
    }
}
