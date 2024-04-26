package onlinestore.service;

import onlinestore.model.product.Product;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @Test
    void getAvailableProducts() {
        ProductService productService = new ProductService();

        List<Product> availableProducts = productService.getAvailableProducts();

        assertEquals(4, availableProducts.size());
        assertEquals("Banana", availableProducts.get(0).getName());
        assertEquals("Apple", availableProducts.get(1).getName());
        assertEquals("Meat", availableProducts.get(2).getName());
        assertEquals("Milk", availableProducts.get(3).getName());

    }

    @Test
    void getProduct() {
        ProductService productService = new ProductService();

        Product product = productService.getProduct(1);

        assertEquals(1, product.getId());
        assertEquals("Banana", product.getName());
        assertEquals(1, product.getPrice());
        assertEquals(1, product.getQuantity());
    }
}