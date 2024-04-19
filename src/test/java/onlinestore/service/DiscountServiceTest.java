package onlinestore.service;

import onlinestore.model.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountServiceTest {
    @Test
    void getDiscountByValidPromocode() {
        DiscountService discountService = new DiscountService();

        assertEquals(50, discountService.getDiscountByPromocode("PROMO2024"));
    }

    @Test
    void getDiscountByInvalidPromocode() {
        DiscountService discountService = new DiscountService();

        assertEquals(0, discountService.getDiscountByPromocode("DISCOUNT2024"));
    }

    @Test
    void applyDiscount() {
        int expectedValue = 75;
        int total = 150;
        DiscountService discountService = new DiscountService();
        Order order = Order.builder().total(total).build();
        discountService.applyDiscount(50, order);
        assertEquals(expectedValue, order.getTotal());
    }
}