package onlinestore.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class Order {
    private final List<Product> products = new ArrayList<>();
    private float total;
    private long orderId;
    private boolean isComplete;
}
