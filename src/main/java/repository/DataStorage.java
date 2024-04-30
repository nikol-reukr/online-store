package repository;

import onlinestore.model.order.Order;
import onlinestore.model.product.Measure;
import onlinestore.model.product.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataStorage {
    public static final List<Product> PRODUCTS = List.of(
            new Product(UUID.randomUUID(), "Sofa", new BigDecimal(17000), "Sofa manufactirer", Measure.ITEM),
            new Product(UUID.randomUUID(), "Armchair", new BigDecimal(8000), "Armchair manufactirer", Measure.ITEM)
    );

    public static List<Order> orders = new ArrayList<>();

}
