package onlinestore;

import onlinestore.exceptions.ProductNotFoundException;

import java.util.List;

public class ProductCatalogue {
    private final List<Product> products = List.of(
            Product.builder()
                    .id(1)
                    .name("Banana")
                    .price(1)
                    .quantity(1)
                    .build(),
            Product.builder()
                    .id(2)
                    .name("Apple")
                    .price(2)
                    .quantity(1)
                    .build(),
            Product.builder()
                    .id(3)
                    .name("Meat")
                    .price(3)
                    .quantity(1)
                    .build(),
            Product.builder()
                    .id(4)
                    .name("Milk")
                    .price(4)
                    .quantity(1)
                    .build()
    );

    public void viewProducts() {
        products.forEach(product -> System.out.printf("%s. %s: quantity %s, price %s%n", product.getId(), product.getName(), product.getQuantity(), product.getPrice()));
    }

    public Product getProduct(int productId) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == productId) {
                return products.get(i);
            }
        }
        throw new ProductNotFoundException("Can't find product by id: " + productId);
    }
}
