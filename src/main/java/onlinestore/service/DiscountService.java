package onlinestore.service;

import onlinestore.model.Order;

import java.util.Map;

public class DiscountService {
    private final Map<String, Integer> promocodes = Map.of(
            "PROMO2024", 50,
            "BIGBOSS007", 99
    );

    public Integer getDiscountByPromocode(String code) {
        return promocodes.getOrDefault(code, 0);
    }

    public void applyDiscount(Integer discount, Order order) {
        float total = order.getTotal();
        order.setTotal(total - (total * discount / 100));
    }
}
