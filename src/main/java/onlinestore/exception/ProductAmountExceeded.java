package onlinestore.exception;

public class ProductAmountExceeded extends RuntimeException {
    public ProductAmountExceeded(String message) {
        super(message);
    }
}
