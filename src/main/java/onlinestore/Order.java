package onlinestore;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Order {
    private final List<Product> products = new ArrayList<>();
    @Setter
    private float total;
    @Setter
    private int orderId;

    public void addProduct(Product product) {
        products.add(product);
    }

    public void viewProducts() {
        products.forEach(product -> System.out.printf("%s%n", product.getName()));
    }
}
