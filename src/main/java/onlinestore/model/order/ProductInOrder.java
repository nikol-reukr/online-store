package onlinestore.model.order;

import lombok.Getter;
import lombok.Setter;
import onlinestore.model.product.Product;

@Getter
@Setter
public class ProductInOrder {
    private Product product;
    private float quantity;
}
