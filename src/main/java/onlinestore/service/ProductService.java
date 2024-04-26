package onlinestore.service;

import onlinestore.exception.ProductNotFoundException;
import onlinestore.model.product.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductService {
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

    public List<Product> getAvailableProducts() {
        return products.stream()
                .filter(product -> product.getQuantity() > 0)
                .collect(Collectors.toList());
    }

    public Product getProduct(long productId) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == productId) {
                return products.get(i);
            }
        }
        throw new ProductNotFoundException("Can't find product by id: " + productId);
    }
}
