package onlinestore.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Order {
    private final List<Product> products = new ArrayList<>();
    private float total;
    private long orderId;
    private boolean isComplete;
}
