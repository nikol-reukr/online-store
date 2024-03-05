package onlinestore;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OrderService orderService = new OrderService();
        boolean exit = false;

        do {
            System.out.println("Welcome to the Groceries Store");
            System.out.println("______________________________");
            System.out.println("1. Make Order");
            System.out.println("2. View Order History");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    orderService.handleOrder();
                    break;
                case 2:
                    orderService.viewHistory();
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
