package onlinestore.model.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class Order {
    private long id;
    private final List<ProductInOrder> products = new ArrayList<>();
    private BigDecimal totalPrice;
    private Client client;
    private OrderState orderState;
    private DevileryOption devileryOption;
    private String deliveryDetails;
}
