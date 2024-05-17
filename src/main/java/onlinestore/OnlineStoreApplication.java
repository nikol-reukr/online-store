package onlinestore;

import onlinestore.controller.OrderController;
import onlinestore.service.DiscountService;
import onlinestore.service.OrderService;
import onlinestore.service.ProductService;

import java.util.ArrayList;
import java.util.Scanner;

public class OnlineStoreApplication {
    private final Scanner inputScanner;
    private final OrderController orderController;

    public OnlineStoreApplication() {
        this.inputScanner = new Scanner(System.in);
        this.orderController = new OrderController(this.inputScanner, new OrderService(new ArrayList<>()), new ProductService(), new DiscountService());
    }

    public static void main(String args) {
        new OnlineStoreApplication().start();
    }

    public void start() {
        boolean exit = false;

        do {
            System.out.println("Welcome to the Groceries Store");
            System.out.println("______________________________");
            System.out.println("1. Make Order");
            System.out.println("2. View Order History");
            System.out.println("0. Exit");

            int choice = inputScanner.nextInt();

            switch (choice) {
                case 1:
                    orderController.createOrder();
                    break;
                case 2:
                    orderController.viewOrderHistory();
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Cashier is closed");
            }
        } while (!exit);
    }
}
